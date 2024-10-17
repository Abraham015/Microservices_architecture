package dev.abraham.ecommerce.repository;

import dev.abraham.ecommerce.model.OrderLine;
import dev.abraham.ecommerce.response.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
