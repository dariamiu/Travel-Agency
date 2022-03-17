package repository;

import model.Destination;
import model.VacationPackage;

import javax.persistence.*;
import java.util.List;

public class DestinationRepository {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel_agency");

    public void insertDestination(Destination destination){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    public List<Destination> getAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT d from Destination d", Destination.class).getResultList();
        }catch (NoResultException e){
            System.out.println("No destination found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void deleteDestination(Destination destination){
        VacationPackageRepository vacationPackageRepository = new VacationPackageRepository();
        for (VacationPackage vacationPackage : destination.getVacationPackageList()) {
            vacationPackageRepository.deleteOkCred(vacationPackage.getName());
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination destination1 = em.createQuery(
                "SELECT d from Destination d WHERE d.name = :name", Destination.class).setParameter("name",destination.getName()).getSingleResult();
        em.remove(destination1);
        em.getTransaction().commit();
        em.close();
    }

    public void updateDetails(String name, String description){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            Query q = em.createQuery(
                    "UPDATE Destination SET description = :description WHERE name = :name");
            q.setParameter("description",description);
            q.setParameter("name",name);
            q.executeUpdate();
        }catch (NoResultException e){
            System.out.println("No destination found");
        }
        em.getTransaction().commit();
        em.close();

    }

    public Destination findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery(
                    "SELECT d from Destination d WHERE d.name = :name", Destination.class).setParameter("name",name).getSingleResult();
        }catch (NoResultException e){
            System.out.println("No destination found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

}
