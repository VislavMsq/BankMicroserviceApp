package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.dto.ProductUpdateDto;
import bankmicroservicesapp.entity.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product update(Product from, @MappingTarget Product to);

    Product toObj(ProductUpdateDto productDto);


    @Mapping(source = "id", target = "id", qualifiedByName = "UUIDToString")
    @Mapping(source = "interestRate", target = "interestRate", qualifiedByName = "toDouble")
    List<ProductDto> toDto(List<Product> productList);

    ProductUpdateDto toDto(Product product);

    @Named("UUIDToString")
    default String UUIDToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("toDouble")
    default Double toDoubleParse(String string) {
        return Double.parseDouble(string);
    }
}
