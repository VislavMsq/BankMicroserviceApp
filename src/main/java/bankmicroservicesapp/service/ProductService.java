package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.entity.Product;

public interface ProductService {
    Product updateProduct(ProductDto productDto);
}
