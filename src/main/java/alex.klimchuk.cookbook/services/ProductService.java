package alex.klimchuk.cookbook.services;

import alex.klimchuk.cookbook.dto.ProductDto;
import alex.klimchuk.cookbook.domain.Product;

import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 31.10.2021.
 */
public interface ProductService {

    List<Product> getAllProducts();

    Product getById(/*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id);

    Product saveOrUpdate(Product product);

    void deleteById(/*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id);

    Product saveOrUpdateProductDto(ProductDto ProductDto);

    void sendMessage(String id);

}
