package alex.klimchuk.cookbook.domain;

import jakarta.persistence.*;
import lombok.*;
//import org.springframework.data.redis.core.RedisHash;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
//@NodeEntity -- annotation for Neo4j
//@RedisHash("products") -- annotation for Redis
//@Document(name = "products") -- annotation for MongoDB
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GraphId -- annotation for Neo4j
    //@PrimaryKey -- annotation for CassandraDB
    //@CassandraType(type = DataType.Name.UUID) -- annotation for CassandraDB
    private /*ObjectId -- type for MongoDB / UUID -- type for CassandraDB / String -- type for NoSql DB*/ Long id;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private boolean messageReceived;

    private Integer messageCount = 0;

}
