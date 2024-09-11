package com.shop.aerostore.interfaces;

import com.shop.aerostore.entity.Product;
import com.shop.aerostore.model.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductInterface {

    List<ProductDTO> getAllProducts();

    String postProduct(Product product);

    ProductDTO putProduct(Integer productId, Product productDetails);

    ProductDTO patchProduct(Integer productId, Map<String, Object> updates);

    String deleteProduct(Integer productId);
}
