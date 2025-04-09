package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "leverancier" , schema = "aircocoolflow")
public class Leverancier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naam", nullable = false, length = 255)
    private String naam;

    @Column(name = "achternaam", nullable = false, length = 255)
    private String achternaam;

    @Column(name = "truckNummer", nullable = false)
    private int trucknummer;

    public Leverancier() {

    }



    // invoegen van informatie
    public Leverancier(String naam, String achternaam, int trucknummer) {
        this.naam = naam;
        this.achternaam = achternaam;
        this.trucknummer = trucknummer;
    }

    // wijzigen van data
    public Leverancier(Integer id, String naam, String achternaam, int trucknummer) {
        this.id = id;
        this.naam = naam;
        this.achternaam = achternaam;
        this.trucknummer = trucknummer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getTrucknummer() {
        return trucknummer;
    }

    public void setTrucknummer(int trucknummer) {
        this.trucknummer = trucknummer;
    }

    @Override
    public String toString() {
        return "Leverancier{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", trucknummer=" + trucknummer +
                '}';
    }
}
