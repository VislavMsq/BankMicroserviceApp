package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.plugEnum.PlugStatusAccount;
import bankmicroservicesapp.entity.plugEnum.PlugTypeAccount;
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
@Table(name = "accounts", schema = "bankdatabase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {


    @Id // primary kay
    @GeneratedValue(strategy = GenerationType.AUTO) // генерация UUID
    @Column(name = "id") // название колонки
    private UUID id;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private User user;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_type")
    private PlugTypeAccount type;

    @Column(name = "account_status")
    private PlugStatusAccount status;

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
                ", clientId=" + user +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                ", currencyCode=" + currencyCode +
                ", bankRating=" + bankRating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}
