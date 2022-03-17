package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserRepository {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void insertUser(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public User findByEmail(String email){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT u from User u WHERE u.email = :email", User.class).setParameter("email",email).getSingleResult();
        }catch (NoResultException e){
            System.out.println("No user found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }



}
