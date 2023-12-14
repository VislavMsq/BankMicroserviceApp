package com.bankmicroservicesapp.entity;

import com.bankmicroservicesapp.entity.enums.StatusProduct;
import com.bankmicroservicesapp.entity.enums.TypeProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "products")
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
    @Enumerated(EnumType.STRING)
    private TypeProduct productType;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private StatusProduct productStatus;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Set<Agreement> agreements;

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && productType == product.productType && productStatus == product.productStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, productStatus);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", productStatus=" + productStatus +
                ", interestRate=" + interestRate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}