package commands;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AddressesWithEmployeeCount implements Command {

    @Override
    public void execute(EntityManager entityManager) {
        TypedQuery<Address> query = entityManager
                .createQuery("SELECT a FROM Address a " +
                        "ORDER BY a.employees.size desc, a.town.id", Address.class);

        query.setMaxResults(10)
                .getResultStream()
                .forEach(a -> System.out.printf("%s, %s - %d employees\n",
                        a.getText(),
                        a.getTown().getName(),
                        a.getEmployees().size()));
    }
}
