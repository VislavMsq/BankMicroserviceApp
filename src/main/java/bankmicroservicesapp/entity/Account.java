package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.plugEnum.StatusAccount;
import bankmicroservicesapp.entity.plugEnum.TypeAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "accounts", schema = "bankdatabase")
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
    private TypeAccount type;

    @Column(name = "account_status")
    private StatusAccount status;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency_code")
    private int currencyCode;

    @Column(name = "bank_rating")
    private int bankRating;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Set<Agreement> agreements;

    @OneToMany(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Set<Transaction> debits;

    @OneToMany(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Set<Transaction> credits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(user, account.user) && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name);
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
                ", user=" + user +
                ", agreements=" + agreements +
                ", debits=" + debits +
                ", credits=" + credits +
                '}';
    }
}
