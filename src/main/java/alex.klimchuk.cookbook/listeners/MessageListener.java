package alex.klimchuk.cookbook.listeners;

import alex.klimchuk.cookbook.configs.JmsConfig;
import alex.klimchuk.cookbook.domain.Product;
import alex.klimchuk.cookbook.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Copyright Alex Klimchuk (c) 31.10.2021.
 */
@Slf4j
@Component
public class MessageListener {

    private ProductRepository productRepository;

    public MessageListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @JmsListener(destination = JmsConfig.PRODUCT_MESSAGE_QUEUE, containerFactory = "jmsFactory") // ActiveMQ
    public void receiveMessage(Map<String, String> message) {
        log.debug("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));

        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);

        productRepository.save(product);
        log.debug("Message processed...");
    }

}
