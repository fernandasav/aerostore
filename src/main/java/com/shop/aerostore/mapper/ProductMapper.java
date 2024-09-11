package com.shop.aerostore.mapper;

import com.shop.aerostore.entity.Product;
import com.shop.aerostore.model.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> toListDto(List<Product> entities);

    ProductDTO toDto(Product entity);

}
