package bankmicroservicesapp.mapper;

import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.entity.enums.StatusProduct;
import bankmicroservicesapp.entity.enums.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Entity
    @Table(name = "products", schema = "bankdatabase")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private UUID id;

        @Column(name = "product_type")
        @Enumerated(EnumType.STRING)
        private TypeProduct productType;

        @Column(name = "product_status")
        @Enumerated(EnumType.STRING)
        private StatusProduct productStatus;

        @Column(name = "interest_rate")
        private double interestRate;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY,
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
                    ", agreements=" + agreements +
                    '}';
        }
    }
}
