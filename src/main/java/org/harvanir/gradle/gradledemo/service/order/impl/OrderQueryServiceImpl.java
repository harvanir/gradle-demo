package org.harvanir.gradle.gradledemo.service.order.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.harvanir.gradle.gradledemo.entity.EntityBeanMapper;
import org.harvanir.gradle.gradledemo.entity.model.QOrder;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.harvanir.gradle.gradledemo.service.order.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author Harvan Irsyadi */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  private OrderRepository orderRepository;

  private EntityBeanMapper mapper;

  @Autowired
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Autowired
  public void setMapper(EntityBeanMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<OrderResponse> findById(Long id) {
    return orderRepository.findOne(QOrder.order.id.eq(id)).map(mapper::toDto);
  }
}
