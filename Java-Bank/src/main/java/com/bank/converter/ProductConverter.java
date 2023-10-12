package com.bank.converter;

import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements EntityConverter<Product, ProductDto>{

    @Autowired
    private CurrencyService currencyService;

    @Override
    public ProductDto toDto(Product product) {
        String managerName = new StringBuilder().append(product.getManager().getPersonalData().getFirstName())
                .append(" ").append(product.getManager().getPersonalData().getFirstName()).toString();

        return new ProductDto(product.getId(), managerName, product.getName(), product.isStatus(),
                product.getCurrency().getCurrencyAbb(), product.getInterestRate(),
                product.getLimit(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(), null, productDto.getName(), productDto.isStatus(),
                currencyService.getCurrencyByAbb(productDto.getCurrencyAbb()), productDto.getInterestRate(),
                productDto.getLimit(), productDto.getCreatedAt(), productDto.getUpdatedAt());
    }
}