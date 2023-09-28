package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.mapper.AgreementMapper;

public interface ProductService {
    AgreementMapper.Product updateProduct(ProductDto productDto);
}
