package bankmicroservicesapp.mapper;

import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.entity.enums.StatusProduct;
import bankmicroservicesapp.entity.enums.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

}
