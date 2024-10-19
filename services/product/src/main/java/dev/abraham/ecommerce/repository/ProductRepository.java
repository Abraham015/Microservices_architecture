package dev.abraham.ecommerce.repository;

import dev.abraham.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
    Optional<Product> findTopByOrderByIdDesc();
}
