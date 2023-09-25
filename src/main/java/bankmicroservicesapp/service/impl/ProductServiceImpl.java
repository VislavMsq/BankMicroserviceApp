package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.Product;
import bankmicroservicesapp.repository.ProductRepository;
import bankmicroservicesapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAll() {
        return productRepository.findAll(Example.of(new Product()));
    }
}
