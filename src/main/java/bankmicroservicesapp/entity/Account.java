package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.enums.Currency;
import bankmicroservicesapp.entity.enums.StatusAccount;
import bankmicroservicesapp.entity.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private TypeAccount type;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;

    @Column(name = "bank_rating")
    private Integer bankRating;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private User user;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private List<Agreement> agreements;

    @OneToMany(mappedBy = "debitAccount",fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private List<Transaction> debits;

    @OneToMany(mappedBy = "creditAccount",fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private List<Transaction> credits;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                ", currencyCode=" + currencyCode +
                ", bankRating=" + bankRating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
