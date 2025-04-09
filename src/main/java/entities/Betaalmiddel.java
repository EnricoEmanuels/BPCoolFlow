package entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "betaalmiddel", schema = "aircocoolflow")
public class Betaalmiddel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "methode", nullable = false, length = 255)
    private String methode;



    @ManyToMany
    @JoinTable(name = "klant_betaalmiddel",
            joinColumns = @JoinColumn(name = "betaalmiddel_id"),
            inverseJoinColumns = @JoinColumn(name = "klant_id"))
    private Set<Klant> klants = new LinkedHashSet<>();

    public Betaalmiddel() {}


    public Betaalmiddel(Integer id, String methode) {
        this.id = id;
        this.methode = methode;
    }

    public Betaalmiddel(String methode) {
        this.methode = methode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public Set<Klant> getKlants() {
        return klants;
    }

    public void setKlants(Set<Klant> klants) {
        this.klants = klants;
    }

    @Override
    public String toString() {
        return "Betaalmiddel{" +
                "id=" + id +
                ", methode='" + methode + '\'' +
                ", klants=" + klants +
                '}';
    }

}