package bankmicroservicesapp.repository;

import bankmicroservicesapp.mapper.AgreementMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<AgreementMapper.Product, UUID> {
}
