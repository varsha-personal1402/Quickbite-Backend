package com.quickbite_backend.quickbite_backend.service;

import com.quickbite_backend.quickbite_backend.model.Seller;
import com.quickbite_backend.quickbite_backend.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller signup(Seller seller) {
        if (sellerRepository.findByEmail(seller.getEmail()) != null) {
            return null;
        }
        return sellerRepository.save(seller);
    }

    public Seller login(String email, String password) {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller != null && seller.getPassword().equals(password)) {
            return seller;
        }
        return null;
    }

    // ✅ Helper method
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
}
