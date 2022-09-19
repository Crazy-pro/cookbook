package alex.klimchuk.cookbook.converters;

import alex.klimchuk.cookbook.dto.ProductDto;
import alex.klimchuk.cookbook.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
//import org.bson.types.ObjectId;

/**
 * Copyright Alex Klimchuk (c) 31.10.2021.
 */
@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {

    @Override
    public Product convert(ProductDto ProductDto) {
        Product product = Product.builder()
                .description(ProductDto.getDescription())
                .price(ProductDto.getPrice())
                .imageUrl(ProductDto.getImageUrl())
                .build();

        if (ProductDto.getId() != null && !StringUtils.isEmpty(ProductDto.getId())) {
            product.setId(/*new ObjectId -- type for MongoDB*/ ProductDto.getId());
        }

        return product;
    }

}
