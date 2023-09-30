package com.bank.converter;

import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements EntityConverter<Product, ProductDto>{
    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), new ManagerConverter().toDto(product.getManager()),
                product.getName(), product.isStatus(), product.getCurrency(), product.getInterestRate(),
                product.getLimit(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(), new ManagerConverter().toEntity(productDto.getManager()),
                productDto.getName(), productDto.isStatus(), productDto.getCurrency(), productDto.getInterestRate(),
                productDto.getLimit(), productDto.getCreatedAt(), productDto.getUpdatedAt());
    }
}