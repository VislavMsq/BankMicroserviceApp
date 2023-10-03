package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.entity.Product;
import bankmicroservicesapp.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/update")
    public Product updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping ("/get/interestRate")
    public List<ProductDto> findProductAgreementStatus(@Param("quantityAgreement") Double quantityAgreement){
        return productService.findAllProductWhereAgreementQuantityMoreThan(quantityAgreement);
    }
}
