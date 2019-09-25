package org.harvanir.gradle.gradledemo.service.order.impl;

import static org.harvanir.gradle.gradledemo.entity.EntityMapper.MAPPER;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.harvanir.gradle.gradledemo.entity.model.QOrder;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.harvanir.gradle.gradledemo.service.order.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  private OrderRepository orderRepository;

  @Autowired
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
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
