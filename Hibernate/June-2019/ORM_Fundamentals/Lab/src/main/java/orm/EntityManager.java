package orm;

import orm.annotations.Column;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityManager<E> implements DbContext<E> {
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES(%s)";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE id = %s";
    private static final String SELECT_QUERY = "SELECT * FROM %s WHERE 1";

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idField = this.getId(entity.getClass());
        idField.setAccessible(true);
        Object value = idField.get(entity);

        if (value == null || (int)value <= 0) {
            return this.doInsert(entity);
        }

        return doUpdate(entity, idField);
    }

    public Iterable<E> find(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return this.find(table, null);
    }

    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tableName = this.getTableName(table);
        String query = String.format(SELECT_QUERY + (where != null ? " AND " + where : ""),
                tableName);
        Statement statement = this.connection.createStatement();
        ResultSet rc = statement.executeQuery(query);

        List<E> entities = new ArrayList<>();
        while (rc.next()) {
            entities.add(this.mapResultToEntity(rc, table));
        }

        return entities;
    }

    public E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return this.find(table, "1 LIMIT 1").iterator().next();
    }

    public E findFirst(Class<E> table, String where) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return this.find(table, where + " LIMIT 1").iterator().next();
    }

    private boolean doInsert(E entity) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String[] fieldsName = this.getFieldsName(entity.getClass());
        String[] fieldsValue = this.getFieldsValue(entity);

        String query = String.format(INSERT_QUERY,
                tableName,
                String.join(", ", fieldsName),
                String.join(", ", fieldsValue));

        return this.connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field idField) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String[] fieldsName = this.getFieldsName(entity.getClass());
        String[] fieldsValue = this.getFieldsValue(entity);

        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < fieldsName.length; i++) {
            pairs.add(fieldsName[i] + " = " + fieldsValue[i]);
        }

        Object id = idField.get(entity);
        String query = String.format(UPDATE_QUERY,
                tableName,
                String.join(", ", pairs),
                id);

        return this.connection.prepareStatement(query).execute();
    }

    private E mapResultToEntity(ResultSet rc, Class<E> table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        E inst = table.getDeclaredConstructor().newInstance();
        
        Arrays.stream(inst.getClass().getDeclaredFields())
                .forEach(a -> {
                    Object type = a.getType();
                    String fieldName = a.getName();
                    Object fieldValue = null;
                    a.setAccessible(true);
                    try {
                        fieldValue = this.getFieldValue(type, fieldName, rc);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        a.set(inst, fieldValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        return inst;
    }

    private Object getFieldValue(Object type, String fieldName, ResultSet rc) throws SQLException {
        Object fieldValue = null;
        if (type == int.class) {
            fieldValue =  rc.getInt(fieldName);
        } else if (type == String.class) {
            fieldValue = rc.getString(fieldName);
        } else if (type == Date.class) {
            fieldValue = rc.getDate(fieldName);
        }
        return fieldValue;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                    new UnsupportedOperationException("Entity does not have primary key."));
    }

    private String getTableName(Class aClass) {
        return aClass.getSimpleName().toLowerCase() + "s";
    }

    private String[] getFieldsName(Class aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(a -> a.isAnnotationPresent(Column.class))
                .map(Field::getName)
                .toArray(String[]::new);
    }

    private String[] getFieldsValue(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(a -> a.isAnnotationPresent(Column.class))
                .map(a -> {
                    try {
                        a.setAccessible(true);
                        return "'" + a.get(entity) + "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .toArray(String[]::new);
    }
}
