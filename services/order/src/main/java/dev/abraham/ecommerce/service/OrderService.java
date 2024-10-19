package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.client.CustomerClient;
import dev.abraham.ecommerce.client.PaymentClient;
import dev.abraham.ecommerce.client.ProductClient;
import dev.abraham.ecommerce.exception.BusinessException;
import dev.abraham.ecommerce.kafka.OrderProducer;
import dev.abraham.ecommerce.mapper.OrderMapper;
import dev.abraham.ecommerce.model.Order;
import dev.abraham.ecommerce.repository.OrderRepository;
import dev.abraham.ecommerce.request.*;
import dev.abraham.ecommerce.response.CustomerResponse;
import dev.abraham.ecommerce.response.OrderResponse;
import dev.abraham.ecommerce.response.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //check the customer->customer-service
        CustomerResponse customer=customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Customer does not exist"));

        //purchase the products->product-service (RestTemplate)
        List<PurchaseResponse> purchaseProducts=productClient.purchaseProducts(request.products());
        //persist order
        BigDecimal amount=new BigDecimal("0");

        for (PurchaseResponse purchase: purchaseProducts){
            amount = amount.add(new BigDecimal(purchase.quantity()).multiply(purchase.price()));
        }

        Order preOrder=orderMapper.toOrder(request);
        preOrder.setAmount(amount);
        Order order=orderRepository.save(preOrder);
        //persist order lines
        for (PurchaseRequest purchase: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchase.productId(),
                            purchase.quantity()
                    )
            );
        }

        //start payment process->payment-service
        PaymentRequest paymentRequest=new PaymentRequest(
                amount,
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        //send the order confirmation->notification-service (using kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(()->new EntityNotFoundException("Order does not exist"));
    }
}
