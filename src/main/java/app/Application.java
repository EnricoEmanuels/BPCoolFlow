package app;

import config.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import DAO.BestellingDaoJpa;
import DAO.KlantDaoJpa;
import DAO.LeverancierDaoJpa;
import DAO.ProductDaoJpa;
import entities.Klant;
import entities.Product;
import entities.Leverancier;
import entities.Bestelling;
import entities.Betaalmiddel;
import DAO.BetaalmiddelDaoJpa;
import services.BetaalmiddelService;

import java.sql.Date;


public class Application {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = JPAConfig.getEntityMangerFactory();
        EntityManager entityManager = JPAConfig.getEntityManger();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

//            JPAConfig config = new JPAConfig();

            KlantDaoJpa klantdaojpa = new KlantDaoJpa(entityManager);
            BestellingDaoJpa bestellingdaojpa = new BestellingDaoJpa(entityManager);
            ProductDaoJpa productdaojpa = new ProductDaoJpa(entityManager);
            LeverancierDaoJpa leverancierdaojpa = new LeverancierDaoJpa(entityManager);
            BetaalmiddelDaoJpa betaalmiddeldaojpa = new BetaalmiddelDaoJpa(entityManager);
            BetaalmiddelService betaalmiddelService = new BetaalmiddelService(betaalmiddeldaojpa, klantdaojpa,productdaojpa,bestellingdaojpa,leverancierdaojpa);

//            Betaalmiddel betaalmiddel1 = new Betaalmiddel("mastercard");
//            betaalmiddeldaojpa.save(betaalmiddel1);

//            Product product = new Product("Airco2", 647.80f);
//            productdaojpa.save(product); // n

//            Leverancier leverancier = new Leverancier("hehe", "haha", 1);
//            leverancierdaojpa.save(leverancier); // n

            Date bestellingdatum1 = Date.valueOf("2025-6-25"); // formaat jaar maand dag als je info wilt opslaan over ze
            Date bestellingdatum2 = Date.valueOf("2025-09-01"); // formaat jaar maand dag

            Product productklant = new Product("boek", 1231.99f);

            Leverancier leverancierklant = new Leverancier("timothy", "maredjo", 1);

            Klant klant = new Klant("dion", "ashruf", "74847835", productklant, leverancierklant);

            Date bestellingdatumklant = Date.valueOf("2025-3-15"); // formaat jaar maand dag
            Bestelling bestellingklant = new Bestelling(bestellingdatumklant, klant);

            Date serviceDatum = Date.valueOf("2025-3-25");
//            betaalmiddelService.createBetaalmiddel("Creditcard", "hehehe", "hahaha", "1234-5678-9101-1121", "Airco X200", 499.99f, "Michael", "Smith", 123, serviceDatum);

//            productdaojpa.save(productklant);
//            leverancierdaojpa.save(leverancierklant);
//            klantdaojpa.save(klant);
//            bestellingdaojpa.save(bestellingklant);

            // Alle findById
//            System.out.println(bestellingdaojpa.findById(2));
//            System.out.println(klantdaojpa.findById(2));
//            System.out.println(leverancierdaojpa.findById(4));
//            System.out.println(productdaojpa.findById(1));
//            System.out.println(betaalmiddeldaojpa.findById(2));

             //alle findAll() methodes
//            System.out.println(productdaojpa.findAll());
//            System.out.println(klantdaojpa.findAll());
//            System.out.println(bestellingdaojpa.findAll());
//            System.out.println(leverancierdaojpa.findAll());
//            System.out.println(betaalmiddeldaojpa.findAll());



//            productdaojpa.deleteById(8); // De kolom product_id in klant verwijst naar de id van product. Dit betekent dat je geen product kunt verwijderen als er nog klanten zijn die ernaar verwijzen.
//            leverancierdaojpa.deleteById(9); // de kolom leverancier_id in klant verwijst naar de id van leverancier. Dit betekent dat je geen leverancier kunt verwijderne als er nog klanten zijn die ernaar verwijzen.
//            bestellingdaojpa.deleteById(5); //dit werkt delete voor bestelling
//            klantdaojpa.deleteById(6); // ja het werkt voor klant en leverancier
//            betaalmiddeldaojpa.deleteById(8); //lekker

            // Alle updates hier

            // Succesvolle bijwerking v product
            Product updateproduct = new Product(2, "deze airco is al gerepareerd", 999.99f);
//            productdaojpa.update(updateproduct);

            //  Succesvolle bijwerking v klant
            Product bestaandeproduct = productdaojpa.findById(7);
            Leverancier bestaandeleverancier = leverancierdaojpa.findById(8);
            Klant updateklant = new Klant(9, "Milton", "Henri", "2445321", bestaandeproduct, bestaandeleverancier);
//            klantdaojpa.update(updateklant);

            // Succesvolle bijwerking van leverancier
            Leverancier updateleverancier = new Leverancier(8, "Donavan", "Frangie", 4);
//            leverancierdaojpa.update(updateleverancier); // het werkt bij leverancier

            // Succesvolle bijwerking van bestelling
            Date bestellingdatum = Date.valueOf("2025-07-17"); // formaat jaar maand dag
            Klant bestaandeklant = klantdaojpa.findById(5);
            Bestelling updatebestelling = new Bestelling(2, bestellingdatum, bestaandeklant);
//            bestellingdaojpa.update(updatebestelling);

            //
            Betaalmiddel updatebetaalmiddel = new Betaalmiddel(7, "Etherium");
//            betaalmiddeldaojpa.update(updatebetaalmiddel);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
