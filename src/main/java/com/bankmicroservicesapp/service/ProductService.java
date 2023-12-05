package com.bankmicroservicesapp.service;

import com.bankmicroservicesapp.dto.ProductDto;
import com.bankmicroservicesapp.dto.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductUpdateDto updateProduct(ProductUpdateDto productDto, UUID id);

    List<ProductDto> findAllProductWhereAgreementQuantityMoreThan(Double quantityAgreement);

    ProductDto findProductById(UUID id);
}
