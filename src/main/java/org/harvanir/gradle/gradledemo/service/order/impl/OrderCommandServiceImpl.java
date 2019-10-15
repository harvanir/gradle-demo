package org.harvanir.gradle.gradledemo.service.order.impl;

import java.util.Set;
import org.harvanir.gradle.gradledemo.entity.EntityBeanMapper;
import org.harvanir.gradle.gradledemo.entity.model.Item;
import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.model.OrderItem;
import org.harvanir.gradle.gradledemo.entity.request.order.OrderCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.ItemRepository;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.harvanir.gradle.gradledemo.service.order.OrderCommandService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/** @author Harvan Irsyadi */
@Service
public class OrderCommandServiceImpl implements OrderCommandService {

  private OrderRepository orderRepository;

  private ItemRepository itemRepository;

  private EntityBeanMapper mapper;

  @Autowired
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Autowired
  public void setItemRepository(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Autowired
  public void setMapper(EntityBeanMapper mapper) {
    this.mapper = mapper;
  }

  @Retryable(
      value = {StaleObjectStateException.class, ObjectOptimisticLockingFailureException.class},
      maxAttemptsExpression = "${app.retry.max-attempts}",
      backoff = @Backoff(delayExpression = "${app.retry.delay}"))
  @Transactional
  @Validated
  @Override
  public OrderResponse create(@Validated OrderCreateRequest orderCreateRequest) {
    Order order = saveOrder(orderCreateRequest);
    updateItemQuantity(order.getItems());

    return mapper.toDto(order);
  }

  private Order saveOrder(OrderCreateRequest orderCreateRequest) {
    Order order = mapper.toEntity(orderCreateRequest);
    Set<OrderItem> orderItems = mapper.toEntity(order, orderCreateRequest.getItems());
    order.setItems(orderItems);

    return orderRepository.save(order);
  }

  private void updateItemQuantity(Set<OrderItem> orderItems) {
    for (OrderItem itemRequest : orderItems) {
      Item item = itemRequest.getItem();
      item.setQuantity(item.getQuantity() - itemRequest.getQuantity());

      itemRepository.save(item);
    }
  }
}
