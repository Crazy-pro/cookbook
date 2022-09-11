package alex.klimchuk.cookbook.repositories;

import alex.klimchuk.cookbook.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface ProductRepository extends /*Neo4jRepository*/ CrudRepository<Product,  /*String -- type for NoSqlDB / UUID -- type for CassandraDB*/ Long> {

}
