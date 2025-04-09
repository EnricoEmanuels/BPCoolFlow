package services;

import DAO.*;
import entities.Bestelling;
import entities.Betaalmiddel;
import entities.Leverancier;
import entities.Product;
import entities.Klant;
import DAO.BetaalmiddelDaoJpa;
import jakarta.transaction.Transactional;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class BetaalmiddelService {
    private BetaalmiddelDaoJpa betaalmiddelDaoJpa;
    private KlantDaoJpa klantDaoJpa;
    private ProductDaoJpa productDaoJpa;
    private BestellingDaoJpa bestellingDaoJpa;
    private LeverancierDaoJpa leverancierDaoJpa;

    public BetaalmiddelService(BetaalmiddelDaoJpa betaalmiddelDaoJpa, KlantDaoJpa klantDaoJpa, ProductDaoJpa productDaoJpa, BestellingDaoJpa bestellingDaoJpa, LeverancierDaoJpa leverancierDaoJpa) {
        this.betaalmiddelDaoJpa = betaalmiddelDaoJpa;
        this.klantDaoJpa = klantDaoJpa;
        this.productDaoJpa = productDaoJpa;
        this.bestellingDaoJpa = bestellingDaoJpa;
        this.leverancierDaoJpa = leverancierDaoJpa;
    }

    @Transactional
    public void createBetaalmiddel(String methode, String voornaam, String achternaam, String creditkaartnummer, String productNaam, float productPrijs, String leverancierNaam, String leverancierAchternaam, int truckNummer, Date bestellingdatum) {

        // Product opslaan
        Product product = new Product();
        product.setProductnaam(productNaam);
        product.setProductprijs(productPrijs);
        productDaoJpa.save(product);

        // Leverancier opslaan
        Leverancier leverancier = new Leverancier();
        leverancier.setNaam(leverancierNaam);
        leverancier.setAchternaam(leverancierAchternaam);
        leverancier.setTrucknummer(truckNummer);
        leverancierDaoJpa.save(leverancier);

        // Klant aanmaken
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setCreditcardnummer(creditkaartnummer);
        klant.setProduct(product);
        klant.setLeverancier(leverancier);
        klantDaoJpa.save(klant);

        // Bestelling aanmaken en koppelen aan klant
        Bestelling bestelling = new Bestelling();
        bestelling.setBestellingDatum(bestellingdatum); // datum aangeven als een parameter

        bestelling.setKlant(klant);
        bestellingDaoJpa.save(bestelling);

        // Betaalmiddel aanmaken en koppelen aan klant
        Betaalmiddel betaalmiddel = new Betaalmiddel();
        betaalmiddel.setMethode(methode);

        Set<Klant> klanten = new HashSet<>();
        klanten.add(klant);
        betaalmiddel.setKlants(klanten); // Zorg ervoor dat je setKlanten hebt in de Betaalmiddel-klasse

        betaalmiddelDaoJpa.save(betaalmiddel);
    }

}