package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.client.ProductServiceClient;
import com.maheshtambelabs.dairymgmt.userservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.userservice.exception.ProductServiceException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductServiceClient productServiceClient;

    public Optional<ProductDTO> getProductById(String productId) {
        try {
            log.debug("Fetching product with id: {}", productId);
            ProductDTO product = productServiceClient.getProductById(productId);
            return Optional.ofNullable(product);
        } catch (FeignException.NotFound ex) {
            log.warn("Product not found with id: {}", productId);
            return Optional.empty();
        } catch (FeignException ex) {
            log.error("Error while fetching product with id: {}", productId, ex);
            throw new ProductServiceException("Error while fetching product: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("Unexpected error while fetching product with id: {}", productId, ex);
            throw new ProductServiceException("Unexpected error while fetching product", ex);
        }
    }
}