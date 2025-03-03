package services;

import DAO.BetaalmiddelDaoJpa;
import entities.Bestelling;
import entities.Betaalmiddel;
import entities.Leverancier;
import entities.Product;
import entities.Klant;
import DAO.BetaalmiddelDaoJpa;
import DAO.KlantDaoJpa;
import jakarta.transaction.Transactional;


import java.util.HashSet;
import java.util.Set;


public class BetaalmiddelService {
    private BetaalmiddelDaoJpa betaalmiddelDaoJpa;
    private KlantDaoJpa klantDaoJpa;

    public BetaalmiddelService(BetaalmiddelDaoJpa betaalmiddelDaoJpa, KlantDaoJpa klantDaoJpa) {
        this.betaalmiddelDaoJpa = betaalmiddelDaoJpa;
        this.klantDaoJpa = klantDaoJpa;
    }

    @Transactional
    public void createBetaalmiddel(String methode, String voornaam, String achternaam, String creditkaartnummer, Product product, Bestelling bestelling, Leverancier leverancier) {

        Klant klant = new Klant();

        // Stel de basisgegevens van de klant in
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setCreditcardnummer(creditkaartnummer);
        klant.setProduct(product);



        // Voeg de leverancier toe (One-to-One relatie)
        klant.setLeverancier(leverancier);

        // Voeg de bestelling toe aan de klant (One-to-Many relatie)
//        Set<Bestelling> bestellingen = new HashSet<>();
//        bestellingen.add(bestelling);
//        klant.setBestellingen(bestellingen);

        // Sla de klant op in de database
        klantDaoJpa.save(klant);

        Betaalmiddel betaalmiddel = new Betaalmiddel(methode);

        Set<Klant> klanten = new HashSet<>();
        klanten.add(klant);
        betaalmiddel.setKlants(klanten);
        betaalmiddelDaoJpa.save(betaalmiddel);


    }

}
