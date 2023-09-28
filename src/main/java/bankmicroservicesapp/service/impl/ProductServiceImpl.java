package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.entity.Product;
import bankmicroservicesapp.entity.enums.StatusProduct;
import bankmicroservicesapp.entity.enums.TypeProduct;
import bankmicroservicesapp.repository.ProductRepository;
import bankmicroservicesapp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product updateProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductType(TypeProduct.valueOf(productDto.getProductType()));
        product.setProductStatus(StatusProduct.valueOf(productDto.getProductStatus()));
        product.setInterestRate(Double.parseDouble(productDto.getInterestRate()));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return product;
    }
}
