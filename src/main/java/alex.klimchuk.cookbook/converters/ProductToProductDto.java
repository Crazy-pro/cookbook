package alex.klimchuk.cookbook.converters;

import alex.klimchuk.cookbook.dto.ProductDto;
import alex.klimchuk.cookbook.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .id(product.getId()/*.toHexString()*/)
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

}
