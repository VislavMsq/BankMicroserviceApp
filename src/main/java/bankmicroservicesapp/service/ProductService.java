package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.entity.Product;

import java.util.List;

public interface ProductService {
    Product updateProduct(ProductDto productDto);

    List<ProductDto> findAllProductWhereAgreementQuantityMoreThan(Double quantityAgreement);
}
