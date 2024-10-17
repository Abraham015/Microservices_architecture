package dev.abraham.ecommerce.mapper;

import dev.abraham.ecommerce.model.Order;
import dev.abraham.ecommerce.request.OrderRequest;
import dev.abraham.ecommerce.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
