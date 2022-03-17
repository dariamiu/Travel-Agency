package repository;

import model.User;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class VacationUserRepository {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void bookVacation(String userEmail, String vacationName){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage = em.createQuery(
                "SELECT u from VacationPackage u WHERE u.name = :name",
                VacationPackage.class).setParameter("name",vacationName).getSingleResult();

        User user = em.createQuery(
                "SELECT u from User u WHERE u.email = :email",
                User.class).setParameter("email",userEmail).getSingleResult();

        List<VacationPackage> vacations = user.getBookedVacationsList();
        vacations.add(vacationPackage);
        user.setBookedVacationsList(vacations);

        for (VacationPackage aPackage : user.getBookedVacationsList()) {
            System.out.println(aPackage.getName());
        }

        List<User> users = vacationPackage.getUsers();
        users.add(user);
        vacationPackage.setUsers(users);

        em.merge(user);
        em.merge(vacationPackage);
        em.getTransaction().commit();
        em.close();

    }
}
