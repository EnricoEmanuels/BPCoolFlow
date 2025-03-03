package DAO;

import entities.Leverancier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entities.Klant;

import java.util.List;
import java.util.ArrayList;


public class KlantDaoJpa implements DAO<Klant> {
    private EntityManager entityManager;

    public KlantDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Klant> findAll() {
        List<Klant> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT k FROM Klant k").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Informatie succesvol opgehaald");
        return result;

    }

    @Override
    public void save(Klant klant) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();
            entityManager.persist(klant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        System.out.println("Succesvol ingevoegd");
    }

    public void deleteById(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Klant klant = entityManager.find(Klant.class, id); // Zoek het product via ID
            if (klant != null) {
                entityManager.remove(klant); // Verwijder het product als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    public void update(Klant klant) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(klant); // Update het product
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol gewijzigd");
    }

    @Override
    public Klant findById(Integer id) {
        Klant klant = null;
        try {
            klant = entityManager.find(Klant.class, id); // Zoek de leverancier via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return klant;

    }
}
