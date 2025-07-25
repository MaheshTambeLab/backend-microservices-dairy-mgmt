package com.maheshtambelabs.dairymgmt.productservice.repository;

import com.maheshtambelabs.dairymgmt.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    List<Category> findByParentId(Long parentId);

    List<Category> findByParentIsNull();
}
