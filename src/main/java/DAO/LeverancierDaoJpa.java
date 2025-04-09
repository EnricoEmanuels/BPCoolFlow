package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entities.Leverancier;


import java.util.ArrayList;
import java.util.List;

public class LeverancierDaoJpa implements DAO<Leverancier> {
    private EntityManager entityManager;

    public LeverancierDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Leverancier> findAll() {
        List<Leverancier> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT l FROM Leverancier l").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Informatie succesvol opgehaald");
        return result;

    }

    @Override
    public void save(Leverancier leverancier) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(leverancier);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println("Succesvol ingevoegd");
    }

    public void deleteById(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Leverancier leverancier = entityManager.find(Leverancier.class, id); // Zoek de leverancier via ID
            if (leverancier != null) {
                entityManager.remove(leverancier); // Verwijder de leverancier als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    public void update(Leverancier leverancier) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(leverancier); // Update de leverancier
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol gewijzigd");
    }


    @Override
    public Leverancier findById(Integer id) {
        Leverancier leverancier = null;
        try {
            leverancier = entityManager.find(Leverancier.class, id); // Zoek de leverancier via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return leverancier;

    }
}
