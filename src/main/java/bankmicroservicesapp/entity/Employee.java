package bankmicroservicesapp.entity;

import bankmicroservicesapp.entity.enums.EmployeeStatus;
import bankmicroservicesapp.entity.enums.EmployeeType;
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
@Table(name = "employees", schema = "bankdatabase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private Set<Agreement> agreements;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(user, employee.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", agreements=" + agreements +
                ", userId=" + user +
                '}';
    }
}
