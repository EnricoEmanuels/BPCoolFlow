package services;

import DAO.KlantDaoJpa;
import entities.Klant;
import entities.Leverancier;
import entities.Bestelling;
import entities.Product;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;


//public class KlantenService {
//    private KlantDaoJpa klantdaojpa;
//
//    public KlantenService(KlantDaoJpa klantdaojpa) {
//        this.klantdaojpa = klantdaojpa;
//    }
//
//    @Transactional
//    public void createKlant(String voornaam, String achternaam, String creditkaartnummer, Product product, Bestelling bestelling, Leverancier leverancier) {
//        // Maak een nieuw Klant-object aan
//        Klant klant = new Klant();
//
//        // Stel de basisgegevens van de klant in
//        klant.setVoornaam(voornaam);
//        klant.setAchternaam(achternaam);
//        klant.setCreditcardnummer(creditkaartnummer);
//
//        // Voeg het product toe aan de klant (Many-to-Many relatie)
//        Set<Product> producten = new HashSet<>();
//        producten.add(product);
//        klant.setProducts(producten);
//
//        // Voeg de leverancier toe (One-to-One relatie)
//        klant.setLeverancier(leverancier);
//
//        // Voeg de bestelling toe aan de klant (One-to-Many relatie)
//        Set<Bestelling> bestellingen = new HashSet<>();
//        bestellingen.add(bestelling);
//        klant.setBestellingen(bestellingen);
//
//        // Sla de klant op in de database
//        klantdaojpa.save(klant);
//    }
//
//}
