package bankmicroservicesapp.repository;

import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select a from Agreement a where a.account.user.id = :clientId")
    List<Agreement> findAgreementsClientIdIs(UUID clientId);
}
