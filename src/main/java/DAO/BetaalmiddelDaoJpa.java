package DAO;

import entities.Bestelling;
import entities.Betaalmiddel;
import entities.Klant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class BetaalmiddelDaoJpa implements DAO<Betaalmiddel>{
    private EntityManager entityManager;

    public BetaalmiddelDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Betaalmiddel> findAll() {
        List<Betaalmiddel> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT b FROM Betaalmiddel b").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println("Informatie succesvol opgehaald");
        return result;
    }

    @Override
    public void save(Betaalmiddel betaalmiddel) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(betaalmiddel);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        System.out.println("Succesvol ingevoegd");
    }


    @Override
    public void deleteById(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Betaalmiddel betaalmiddel = entityManager.find(Betaalmiddel.class, id); // Zoek de betaalmiddel via ID
            if (betaalmiddel != null) {
                entityManager.remove(betaalmiddel); // Verwijder de betaalmiddel als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    @Override
    public void update(Betaalmiddel betaalmiddel) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(betaalmiddel); // Update de betaalmiddel
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol gewijzigd");
    }

    @Override
    public Betaalmiddel findById(Integer id) {
        Betaalmiddel betaalmiddel = null;
        try {
            betaalmiddel = entityManager.find(Betaalmiddel.class, id); // Zoek de betaalmiddel via ID
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Informatie van deze succesvol opgehaald");
        return betaalmiddel;

    }


}
