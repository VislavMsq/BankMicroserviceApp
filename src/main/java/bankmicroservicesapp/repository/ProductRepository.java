package bankmicroservicesapp.repository;

import bankmicroservicesapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "select a from Product a where a.interestRate > :quantityAgreement")
    List<Product> findProductAgreementQuantity(Double quantityAgreement);
}
