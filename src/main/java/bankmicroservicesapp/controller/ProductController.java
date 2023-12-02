package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.dto.ProductUpdateDto;
import bankmicroservicesapp.service.ProductService;
import bankmicroservicesapp.validation.annotation.ValidUUID;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/get/by-id/{id}")
    public ProductDto getById(@ValidUUID @PathVariable("id") UUID id) {
        return productService.findProductById(id);
    }

    @RequestMapping(value = "/update/by-id/{id}")
    public ProductUpdateDto updateProduct(@ValidUUID @RequestBody ProductUpdateDto productDto, @PathVariable("id") UUID id) {
        return productService.updateProduct(productDto, id);
    }

    @GetMapping("/get/interestRate")
    public List<ProductDto> findProductAgreementStatus(@Param("quantityAgreement") Double quantityAgreement) {
        return productService.findAllProductWhereAgreementQuantityMoreThan(quantityAgreement);
    }
}
