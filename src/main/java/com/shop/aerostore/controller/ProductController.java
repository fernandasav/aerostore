package com.shop.aerostore.controller;

import com.shop.aerostore.entity.Product;
import com.shop.aerostore.interfaces.ProductInterface;
import com.shop.aerostore.model.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
@Tag(name = "Aerostore Controller", description = "Controller to access Aerostore endpoints.")
public class ProductController {

    private final ProductInterface productInterface;

    public ProductController(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }

    @Operation(summary = "Get All Poducts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found with success.",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDTO.class
                            )
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Error to find products.",
                    content = @Content),
    })
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> result = productInterface.getAllProducts();

        return ResponseEntity.ok().body(result);

    }

    @Operation(summary = "Create a new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product create with success.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDTO.class
                            )
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Error to create a new Product.", content = @Content),
    })

    @PostMapping(path="/postProduct")
    private ResponseEntity<String> postProduct(@RequestBody Product product){

        String result = productInterface.postProduct(product);

        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Edit a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product edit with success.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDTO.class
                            )
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Error to edit the Product.", content = @Content),
    })

    @PutMapping(path="/putProduct/{productId}")
    private ResponseEntity<ProductDTO> putProduct(@PathVariable Integer productId,
                                                  @RequestBody Product productDetails){

        ProductDTO result = productInterface.putProduct(productId,productDetails);

        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Partially Edit a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product partially edited with success.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDTO.class
                            )
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Error to partially edit the Product.", content = @Content),
    })
    @PatchMapping(path="/patchProduct/{productId}")
    private ResponseEntity<ProductDTO> patchProduct(@PathVariable Integer productId,
                                                    @RequestBody Map<String, Object> updates){

        ProductDTO result = productInterface.patchProduct(productId, updates);

        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Delete a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product delete with success.", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDTO.class
                            )
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Error to delete the Product.", content = @Content),
    })
    @DeleteMapping(path="/deleteProduct/{productId}")
    private ResponseEntity<String> deleteProduct(@PathVariable Integer productId){

        String result = productInterface.deleteProduct(productId);

        return ResponseEntity.ok().body(result);
    }


}
