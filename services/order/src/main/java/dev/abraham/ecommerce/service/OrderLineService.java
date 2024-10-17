package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.mapper.OrderLineMapper;
import dev.abraham.ecommerce.model.OrderLine;
import dev.abraham.ecommerce.repository.OrderLineRepository;
import dev.abraham.ecommerce.request.OrderLineRequest;
import dev.abraham.ecommerce.response.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository lineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine order=mapper.toOrderLine(orderLineRequest);
        return lineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return lineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
