package com.bankmicroservicesapp.entity;

import com.bankmicroservicesapp.entity.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_description")
    private String description;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
    private Account debitAccount;

    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH})
    private Account creditAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, description);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
