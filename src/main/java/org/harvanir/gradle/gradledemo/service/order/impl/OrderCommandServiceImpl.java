package org.harvanir.gradle.gradledemo.service.order.impl;

import static org.harvanir.gradle.gradledemo.entity.EntityMapper.MAPPER;

import org.harvanir.gradle.gradledemo.entity.request.order.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.harvanir.gradle.gradledemo.service.order.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

  private OrderRepository orderRepository;

  @Autowired
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public OrderResponse create(CreateOrderRequest createOrderRequest) {
    return MAPPER.toDto(orderRepository.save(MAPPER.toEntity(createOrderRequest)));
  }
}
