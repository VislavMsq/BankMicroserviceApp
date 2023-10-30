package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.dto.ProductUpdateDto;
import bankmicroservicesapp.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductUpdateDto updateProduct(ProductUpdateDto productDto, UUID id);

    List<ProductDto> findAllProductWhereAgreementQuantityMoreThan(Double quantityAgreement);

    ProductDto findProductById(UUID id);
}
