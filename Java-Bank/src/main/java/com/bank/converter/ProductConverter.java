package com.bank.converter;

import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter implements EntityConverter<Product, ProductDto>{

    private final CurrencyService currencyService;

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getId(), product.getName(), product.isStatus(),
                product.getCurrency().getCurrencyAbb(), product.getInterestRate(),
                product.getLimit(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(), null, productDto.getName(),
                productDto.isStatus(), null, productDto.getInterestRate(),
                productDto.getLimit(), productDto.getCreatedAt(), productDto.getUpdatedAt());
    }
}
