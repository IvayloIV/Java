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
    private static final String CREATE_QUERY = "CREATE TABLE %s ( ";
    private static final String ALTER_QUERY = "ALTER TABLE %s ";
    private static final String GET_COLUMNS_NAME = "SELECT `COLUMN_NAME` \n" +
            "FROM `INFORMATION_SCHEMA`.`COLUMNS`\n" +
            "WHERE `TABLE_SCHEMA`='soft_uni' \n" +
            "AND `TABLE_NAME`='%s'; ";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE %s;";

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

    public void doCreate(Class entity) throws SQLException {
        String tableName = this.getTableName(entity);
        StringBuilder query = new StringBuilder(String.format(CREATE_QUERY, tableName));

        Field[] fields = entity.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String sqlType = this.getSQLType(field.getType());
            String fieldName = field.getName();

            query.append(fieldName).append(" ").append(sqlType);
            if (field.isAnnotationPresent(Id.class)) {
                query.append(" PRIMARY KEY AUTO_INCREMENT");
            }
            if (i < fields.length - 1) {
                query.append(", ");
            }
        }

        query.append(")");
        this.connection.createStatement().execute(query.toString());
    }

    public void doAlter(Class entity) throws SQLException {
        String tableName = this.getTableName(entity);
        String query = String.format(ALTER_QUERY, tableName);

        List<String> columnsName = this.getExistColumnsName(tableName);
        Field[] fields = entity.getDeclaredFields();
        List<String> missingColumns = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();

            if (!columnsName.contains(fieldName)) {
                String sqlType = this.getSQLType(field.getType());
                missingColumns.add(String.format("ADD %s %s", fieldName, sqlType));
            }
        }
        query += String.join(", ", missingColumns);

        if (missingColumns.size() > 0) {
            this.connection.createStatement().execute(query);
        }
    }

    @Override
    public void delete(Class<E> table, String where) throws SQLException {
        String tableName = this.getTableName(table);
        String query = String.format(DELETE_QUERY, tableName, where);
        Statement statement = this.connection.createStatement();
        statement.execute(query);
    }

    private List<String> getExistColumnsName(String tableName) throws SQLException {
        String query = String.format(GET_COLUMNS_NAME, tableName);

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString("COLUMN_NAME"));
        }

        return names;
    }

    private String getSQLType(Class type) {
        if (type == int.class) {
            return "INT";
        } else if (type == String.class) {
            return "VARCHAR(50)";
        } else if (type == double.class) {
            return "DOUBLE";
        } else if (type == Date.class) {
            return "DATE";
        }

        return null;
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
