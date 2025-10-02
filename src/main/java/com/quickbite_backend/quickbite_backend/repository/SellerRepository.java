package com.quickbite_backend.quickbite_backend.repository;

import com.quickbite_backend.quickbite_backend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
    Seller findByEmailAndPassword(String email, String password); 
}
