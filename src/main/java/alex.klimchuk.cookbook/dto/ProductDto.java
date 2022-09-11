package alex.klimchuk.cookbook.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private /*ObjectId -- type for MongoDB / UUID -- type for CassandraDB / String -- type for NoSql DB*/ Long id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

}
