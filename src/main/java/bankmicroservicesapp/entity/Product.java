package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.plugEnum.PlugStatusProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "products", schema = "bankdatabase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "agreement", fetch = FetchType.LAZY,
            cascade = {MERGE, PERSIST, REFRESH})
    private Set<Agreement> agreements;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", interestRate=" + interestRate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
