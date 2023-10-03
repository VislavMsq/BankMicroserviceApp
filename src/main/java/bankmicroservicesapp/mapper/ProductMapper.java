package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toUUID")
    @Mapping(source = "interestRate", target = "interestRate", qualifiedByName = "toDouble")
    List<ProductDto> productToProductDto(List<Product> productList);

    @Named("toUUID")
    default UUID toUUIDParse(String string) {
        return UUID.fromString(string);
    }

    @Named("toDouble")
    default Double toDoubleParse(String string) {
        return Double.parseDouble(string);
    }
}
