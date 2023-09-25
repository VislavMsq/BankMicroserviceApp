package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}
