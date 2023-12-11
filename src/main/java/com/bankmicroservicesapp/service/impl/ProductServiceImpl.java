package com.bankmicroservicesapp.service.impl;

import com.bankmicroservicesapp.dto.ProductDto;
import com.bankmicroservicesapp.dto.ProductUpdateDto;
import com.bankmicroservicesapp.entity.Product;
import com.bankmicroservicesapp.exception.DataNotExistException;
import com.bankmicroservicesapp.exception.ErrorMessage;
import com.bankmicroservicesapp.mapper.ProductMapper;
import com.bankmicroservicesapp.repository.ProductRepository;
import com.bankmicroservicesapp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductUpdateDto updateProduct(ProductUpdateDto productDto, UUID id) {
        Product prod = productMapper.update(productMapper.toObj(productDto), productRepository.findById(id)
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));
        productRepository.save(prod);
        return productMapper.toUpdateDto(prod);
    }

    @Override
    public List<ProductDto> findAllProductWhereAgreementQuantityMoreThan(Double quantityAgreement) {
        List<Product> productList = productRepository.findProductAgreementQuantity(quantityAgreement);
        return productMapper.toDto(productList);
    }

    @Override
    public ProductDto findProductById(UUID id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() ->
                new DataNotExistException(ErrorMessage.INVALID_ID)));
    }
}
