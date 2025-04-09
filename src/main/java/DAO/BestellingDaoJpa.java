package DAO;

import entities.Leverancier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entities.Bestelling;


import java.util.ArrayList;
import java.util.List;

public class BestellingDaoJpa implements DAO<Bestelling> {
    private EntityManager entityManager;

    public BestellingDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Bestelling> findAll() {
        List<Bestelling> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT b FROM Bestelling b").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println("Informatie succesvol opgehaald");
        return result;

    }

    @Override
    public void save(Bestelling bestelling) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(bestelling);
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
            Bestelling bestelling = entityManager.find(Bestelling.class, id); // Zoek de bestelling via ID
            if (bestelling != null) {
                entityManager.remove(bestelling); // Verwijder de bestelling als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    public void update(Bestelling bestelling) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(bestelling); // Update de bestelling
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol gewijzigd");
    }

    @Override
    public Bestelling findById(Integer id) {
        Bestelling bestelling = null;
        try {
            bestelling = entityManager.find(Bestelling.class, id); // Zoek de bestelling via ID
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Informatie van deze succesvol opgehaald");
        return bestelling;

    }


}
