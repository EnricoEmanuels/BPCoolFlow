package DAO;

import entities.Bestelling;
import entities.Leverancier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoJpa implements DAO<Product> {
    private EntityManager entityManager;

    public ProductDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT p FROM Product p").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Informatie succesvol opgehaald");
        return result;

    }

    @Override
    public void save(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(product);
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
            Product product = entityManager.find(Product.class, id); // Zoek het product via ID
            if (product != null) {
                entityManager.remove(product); // Verwijder het product als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        System.out.println("Succesvol verwijderd");

    }

    public void update(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(product); // Update het product
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        System.out.println("Succesvol gewijzigd");
    }

    @Override
    public Product findById(Integer id) {
        Product product = null;
        try {
            product = entityManager.find(Product.class, id); // Zoek de leverancier via ID
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Informatie van deze succesvol opgehaald");
        return product;

    }
}