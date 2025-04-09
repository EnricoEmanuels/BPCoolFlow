package entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product" , schema = "aircocoolflow")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productnaam", nullable = false, length = 255)
    private String productnaam;

    @Column(name = "productprijs", nullable = false)
    private float productprijs;

    public Product() {

    }



    //toevoegen van info
    public Product(String productnaam, float productprijs) {
        this.productnaam = productnaam;
        this.productprijs = productprijs;
    }

    //wijzigen van bestaande data
    public Product(Integer id, String productnaam, float productprijs) {
        this.id = id;
        this.productnaam = productnaam;
        this.productprijs = productprijs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductnaam() {
        return productnaam;
    }

    public void setProductnaam(String productnaam) {
        this.productnaam = productnaam;
    }

    public float getProductprijs() {
        return productprijs;
    }

    public void setProductprijs(float productprijs) {
        this.productprijs = productprijs;
    }

//    public Set<Klant> getKlanten() {
//        return klanten;
//    }
//
//    public void setKlanten(Set<Klant> klanten) {
//        this.klanten = klanten;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productnaam='" + productnaam + '\'' +
                ", productprijs=" + productprijs ;
    }
}
