package entities;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "klant" , schema = "aircocoolflow")
public class Klant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "voornaam", nullable = false, length = 255)
    private String voornaam;

    @Column(name = "achternaam", nullable = false, length = 255)
    private String achternaam;

    @Column(name = "creditcardnummer", nullable = false, length = 255)
    private String creditcardnummer;

    @ManyToOne( optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "leverancier_id", referencedColumnName = "id")
    private Leverancier leverancier;

    @OneToMany(mappedBy = "klant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bestelling> bestellingen = new HashSet<>();

    @ManyToMany(mappedBy = "klants")
    private Set<Betaalmiddel> betaalmiddelen = new HashSet<>();

//    @ManyToMany
//    @JoinTable (
//            name = "klant_product",
//            joinColumns = @JoinColumn(name = "klant_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private Set<Product> products = new HashSet<>();

    public Klant() {

    }

//    public Klant(Integer id) {
//        this.id = id;
//    }


    //invoegen van informatie geen id want id wordt automatisch gegenereerd
    public Klant(String voornaam, String achternaam, String creditcardnummer, Product product, Leverancier leverancier) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.creditcardnummer = creditcardnummer;
        this.product = product;
        this.leverancier = leverancier;
    }

    // dit is als je update
    public Klant(Integer id, String voornaam, String achternaam, String creditcardnummer, Product product, Leverancier leverancier) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.creditcardnummer = creditcardnummer;
        this.product = product;
        this.leverancier = leverancier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getCreditcardnummer() {
        return creditcardnummer;
    }

    public void setCreditcardnummer(String creditcardnummer) {
        this.creditcardnummer = creditcardnummer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Leverancier getLeverancier() {
        return leverancier;
    }

    public void setLeverancier(Leverancier leverancier) {
        this.leverancier = leverancier;
    }

    public Set<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(Set<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }


    public Set<Betaalmiddel> getBetaalmiddelen() {
        return betaalmiddelen;
    }

    public void setBetaalmiddelen(Set<Betaalmiddel> betaalmiddelen) {
        this.betaalmiddelen = betaalmiddelen;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", creditcardnummer='" + creditcardnummer + '\'' +
                ", product=" + (product != null ? product.getId() : null) +
                ", leverancier=" + (leverancier != null ? leverancier.getId() : null) +
                '}';
    }
}
