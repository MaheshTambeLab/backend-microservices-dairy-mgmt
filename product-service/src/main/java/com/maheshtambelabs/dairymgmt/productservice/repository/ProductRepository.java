package com.maheshtambelabs.dairymgmt.productservice.repository;

import com.maheshtambelabs.dairymgmt.productservice.model.Category;
import com.maheshtambelabs.dairymgmt.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Mahesh Tambe
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findBySubcategoryId(Long subcategoryId);

    List<Product> findByVendorId(Long vendorId);

    @Modifying
    @Query("UPDATE Product p SET p.usedQuantity = p.usedQuantity + :quantity WHERE p.id = :productId")
    int incrementUsedQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE Product p SET p.usedQuantity = p.usedQuantity - :quantity WHERE p.id = :productId AND p.usedQuantity >= :quantity")
    int decrementUsedQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);
}
