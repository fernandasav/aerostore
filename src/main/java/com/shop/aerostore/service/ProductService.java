package com.shop.aerostore.service;

import com.shop.aerostore.entity.Product;
import com.shop.aerostore.interfaces.ProductInterface;
import com.shop.aerostore.mapper.ProductMapper;
import com.shop.aerostore.model.ProductDTO;
import com.shop.aerostore.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> result = new ArrayList<>();
        try {
            result = productRepository.findAll();
        } catch (Exception Ex){
            System.out.println("Error executing getAllProducts");
        }
        return productMapper.toListDto(result);
    }

    @Override
    public String postProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setProductColor(product.getProductColor());
        newProduct.setProductType(product.getProductType());
        newProduct.setProductPrice(product.getProductPrice());
        newProduct.setProductId(product.getProductId());

        productRepository.save(newProduct);

        String result = "New product added successfully!";

        return result;
    }

    @Override
    public ProductDTO putProduct(Integer productId, Product productDetails) {

        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (!existingProductOptional.isPresent()) {
            System.out.println("Error executing putProduct");
            return null;
          }

            Product product = new Product();
            product.setProductId(productDetails.getProductId());
            product.setProductName(productDetails.getProductName());
            product.setProductColor(productDetails.getProductColor());
            product.setProductType(productDetails.getProductType());
            product.setProductPrice(productDetails.getProductPrice());

            Product updatedProduct = productRepository.save(product);

            return productMapper.toDto(updatedProduct);
        }

    @Override
    public ProductDTO patchProduct(Integer productId, Map<String, Object> updates) {

        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (!existingProductOptional.isPresent()) {
            System.out.println("Error executing patchProduct: Product not found");
            return null;
        }

        Product existingProduct = existingProductOptional.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "productName":
                    existingProduct.setProductName((String) value);
                    break;
                case "productColor":
                    existingProduct.setProductColor((String) value);
                    break;
                case "productType":
                    existingProduct.setProductType((String) value);
                    break;
                case "productPrice":
                    existingProduct.setProductPrice((Double) value);
                    break;
                default:
                    System.out.println("Unknown field: " + key);
            }
        });

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(updatedProduct);
    }

    @Override
    public String deleteProduct(Integer productId) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (!existingProductOptional.isPresent()) {
            System.out.println("Error executing patchProduct: Product not found");
            return null;
        }

        productRepository.deleteById(productId);

        String result = "Product delete successfully!";

        return result;
    }

}

