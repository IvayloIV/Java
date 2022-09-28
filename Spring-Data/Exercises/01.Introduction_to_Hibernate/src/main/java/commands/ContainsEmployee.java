package commands;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.*;

public class ContainsEmployee implements Command {

    @Override
    public void execute(EntityManager entityManager) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                String[] empName = br.readLine().split(" ");

                TypedQuery<Integer> query = entityManager
                        .createQuery("SELECT 1 FROM Employee e " +
                                "WHERE e.firstName = :firstName " +
                                " AND e.lastName = :lastName", Integer.class);

                query.setParameter("firstName", empName[0]);
                query.setParameter("lastName", empName[1]);
                Integer empExist = query.getResultStream().findFirst().orElse(null);

                bw.write(empExist != null ? "Yes" : "No");
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
