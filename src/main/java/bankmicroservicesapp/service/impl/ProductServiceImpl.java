package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.dto.ProductUpdateDto;
import bankmicroservicesapp.entity.Product;
import bankmicroservicesapp.exeption.DataNotExistException;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.mapper.ProductMapper;
import bankmicroservicesapp.repository.ProductRepository;
import bankmicroservicesapp.service.ProductService;
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
        if (!Valid.isValidUUID(id.toString())) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
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
        if (!Valid.isValidUUID(id.toString())) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() ->
                new DataNotExistException(ErrorMessage.INVALID_ID)));
    }
}
