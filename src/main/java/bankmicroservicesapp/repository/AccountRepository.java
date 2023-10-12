package bankmicroservicesapp.repository;

import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.entity.enums.StatusAccount;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(value = "select a from Account a where a.status = :status" )
    List<Account> findAllByStatus(@Param("status") StatusAccount status);
}
