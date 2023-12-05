package com.bankmicroservicesapp.mapper;

import com.bankmicroservicesapp.dto.ProductDto;
import com.bankmicroservicesapp.dto.ProductUpdateDto;
import com.bankmicroservicesapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product update(Product from, @MappingTarget Product to);

    Product toObj(ProductUpdateDto productDto);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "interestRate", target = "interestRate")
    List<ProductDto> toDto(List<Product> productList);

    ProductUpdateDto toUpdateDto(Product product);
    ProductDto toDto(Product product);

}
