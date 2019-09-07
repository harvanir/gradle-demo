package org.harvanir.gradle.gradledemo.service.impl;

import static org.harvanir.gradle.gradledemo.entity.EntityMapper.MAPPER;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.harvanir.gradle.gradledemo.entity.model.QOrder;
import org.harvanir.gradle.gradledemo.entity.request.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.harvanir.gradle.gradledemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  @Autowired
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public OrderResponse create(CreateOrderRequest createOrderRequest) {
    return MAPPER.toDto(orderRepository.save(MAPPER.toEntity(createOrderRequest)));
  }

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll().stream().map(MAPPER::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<OrderResponse> findById(Long id) {
    return orderRepository.findOne(QOrder.order.id.eq(id)).map(MAPPER::toDto);
  }
}
