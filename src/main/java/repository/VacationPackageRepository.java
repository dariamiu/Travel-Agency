package repository;

import model.User;
import model.VacationPackage;

import javax.persistence.*;

import java.util.List;

public class VacationPackageRepository {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void insertVacation(VacationPackage vacationPackage){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }

    public List<VacationPackage> findByDestinationName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT u from VacationPackage u WHERE u.destination.name = :name",
                    VacationPackage.class).setParameter("name",name).getResultList();
        }catch (NoResultException e){
            System.out.println("No vacation found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public VacationPackage findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT u from VacationPackage u WHERE u.name = :name",
                    VacationPackage.class).setParameter("name",name).getSingleResult();
        }catch (NoResultException e){
            System.out.println("No vacation found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }


    public void deleteOkCred(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage = em.createQuery(
                "SELECT d from VacationPackage d WHERE d.name = :name", VacationPackage.class).setParameter("name",name).getSingleResult();
        List<User> users = vacationPackage.getUsers();
        for (User user : users) {
            user.getBookedVacationsList().remove(vacationPackage);
        }
        vacationPackage.setUsers(null);
        em.remove(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }




    public void updateTakenPlacesAndStatus(String name, Integer places, String status){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            Query q = em.createQuery(
                    "UPDATE VacationPackage SET takenPlaces = :places, status = :status WHERE name = :name ");
            q.setParameter("places", places);
            q.setParameter("status",status);
            q.setParameter("name",name);
            q.executeUpdate();
        }catch (NoResultException e){
            System.out.println("No destination with that name found");
        }
        em.getTransaction().commit();
        em.close();

    }

    public void updateStatus(String name, String status){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            Query q = em.createQuery(
                    "UPDATE VacationPackage SET status = :status WHERE name = :name ");
            q.setParameter("status", status);
            q.setParameter("name",name);
            q.executeUpdate();
        }catch (NoResultException e){
            System.out.println("No destination with that name found");
        }
        em.getTransaction().commit();
        em.close();

    }


    public List<VacationPackage> getAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT v from VacationPackage v", VacationPackage.class).getResultList();
        }catch (NoResultException e){
            System.out.println("No destination found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void updateAllVacation(String name, VacationPackage vacationPackage){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            Query q = em.createQuery(
                    "UPDATE VacationPackage SET name = :newName, startDate = :startDate," +
                            "endDate = :endDate, price = :price, availablePlaces = :availablePlaces," +
                            "takenPlaces = :takenPlaces, status = :status WHERE name = :name ");
            q.setParameter("newName", vacationPackage.getName());
            q.setParameter("startDate", vacationPackage.getStartDate());
            q.setParameter("endDate", vacationPackage.getEndDate());
            q.setParameter("price", vacationPackage.getPrice());
            q.setParameter("availablePlaces", vacationPackage.getAvailablePlaces());
            q.setParameter("takenPlaces", vacationPackage.getTakenPlaces());
            q.setParameter("status", vacationPackage.getStatus());
            q.setParameter("name",name);
            q.executeUpdate();
        }catch (NoResultException e){
            System.out.println("No destination with that name found");
        }
        em.getTransaction().commit();
        em.close();
    }




}
