package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.mapper.AgreementMapper;
import bankmicroservicesapp.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/update")
    public AgreementMapper.Product updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }
}
