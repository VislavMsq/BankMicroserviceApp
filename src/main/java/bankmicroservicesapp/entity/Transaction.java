package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "transactions", schema = "bankdatabase")
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
    private double amount;

    @Column(name = "transaction_description")
    private String description;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @JsonIgnore
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Account debitAccountId;

    @JsonIgnore
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Account creditAccountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + updatedAt +
                ", debitAccountId=" + debitAccountId +
                ", creditAccountId=" + creditAccountId +
                '}';
    }
}
