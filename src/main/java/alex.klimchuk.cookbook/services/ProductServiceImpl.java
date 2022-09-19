package alex.klimchuk.cookbook.services;

import alex.klimchuk.cookbook.CookbookApplication;
import alex.klimchuk.cookbook.dto.ProductDto;
import alex.klimchuk.cookbook.converters.ProductDtoToProduct;
import alex.klimchuk.cookbook.domain.Product;
import alex.klimchuk.cookbook.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright Alex Klimchuk (c) 31.10.2021.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductDtoToProduct ProductDtoToProduct;
    private JmsTemplate jmsTemplate;
    private RabbitTemplate rabbitTemplate;

    public ProductServiceImpl(ProductRepository productRepository, ProductDtoToProduct productDtoToProduct,
                              JmsTemplate jmsTemplate, RabbitTemplate rabbitTemplate) {
        this.productRepository = productRepository;
        ProductDtoToProduct = productDtoToProduct;
        this.jmsTemplate = jmsTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(/*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteById(/*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductDto(ProductDto ProductDto) {
        Product savedProduct = saveOrUpdate(ProductDtoToProduct.convert(ProductDto));

        log.debug("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }

    @Override
    public void sendMessage(String id) {
        Map<String, String> actions = new HashMap<>();
        actions.put("id", id);

        log.debug("Sending the index request through queue message");
        jmsTemplate.convertAndSend(CookbookApplication.PRODUCT_MESSAGE_QUEUE, actions);
        //rabbitTemplate.convertAndSend(CookbookApplication.PRODUCT_MESSAGE_QUEUE, actions);
    }

}
