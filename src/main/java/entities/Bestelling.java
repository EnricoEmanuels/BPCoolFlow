package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "bestelling", schema = "aircocoolflow")
public class Bestelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bestellingDatum", nullable = false)
    private Date bestellingDatum;


    @ManyToOne( optional = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "klant_id", nullable = false)
    private Klant klant;

    public Bestelling() {

    }

//    public Bestelling(Integer id) {
//        this.id = id;
//    }

    // dit is voor je update
    public Bestelling(Integer id, Date bestellingDatum, Klant klant) {
        this.id = id;
        this.bestellingDatum = bestellingDatum;
        this.klant = klant;
    }

    // opslaan van data
    public Bestelling(Date bestellingDatum, Klant klant) {
        this.bestellingDatum = bestellingDatum;
        this.klant = klant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBestellingDatum() {
        return bestellingDatum;
    }

    public void setBestellingDatum(Date bestellingDatum) {
        this.bestellingDatum = bestellingDatum;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Bestelling{" +
                "id=" + id +
                ", bestellingDatum=" + bestellingDatum +
                ", klant=" + (klant != null ? klant.getId() : null) +
                '}';
    }
}
