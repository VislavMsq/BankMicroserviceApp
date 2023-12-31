package com.bankmicroservicesapp.entity;

import com.bankmicroservicesapp.entity.enums.Currency;
import com.bankmicroservicesapp.entity.enums.StatusAgreement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;

    @Column(name = "agreement_status")
    @Enumerated(EnumType.STRING)
    private StatusAgreement status;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "agreement_limit")
    private Double agreementLimit;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private Account account;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private Product product;

    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private Employee manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return Double.compare(interestRate, agreement.interestRate) == 0 && Double.compare(sum, agreement.sum) == 0 && Objects.equals(id, agreement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interestRate, sum);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", interestRate=" + interestRate +
                ", currencyCode=" + currencyCode +
                ", status=" + status +
                ", discount=" + discount +
                ", agreementLimit=" + agreementLimit +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
