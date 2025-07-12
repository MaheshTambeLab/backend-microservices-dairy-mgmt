package com.maheshtambelabs.dairymgmt.userservice.client;

import com.maheshtambelabs.dairymgmt.userservice.config.FeignClientConfiguration;
import com.maheshtambelabs.dairymgmt.userservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "product-service",
        url = "${product.service.url}",
        configuration = FeignClientConfiguration.class
)
public interface ProductServiceClient {
    @GetMapping("/products/{productId}")
    ProductDTO getProductById(@PathVariable String productId);
    
    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO);
}