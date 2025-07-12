package com.maheshtambelabs.dairymgmt.userservice.repository;

import com.maheshtambelabs.dairymgmt.userservice.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {
    List<Vendor> findAllByOrderByFarmName();
    List<Vendor> findByFarmNameContainingIgnoreCase(String farmName);
}
