package org.harvanir.gradle.gradledemo.service.order.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.harvanir.gradle.gradledemo.entity.EntityBeanMapperImpl;
import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.repository.OrderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/** @author Harvan Irsyadi */
@RunWith(MockitoJUnitRunner.class)
public class OrderQueryServiceImplTest {

  @InjectMocks private OrderQueryServiceImpl orderService;

  @Mock private OrderRepository orderRepository;

  @Spy private EntityBeanMapperImpl mapper;

  @After
  public void after() {
    verifyNoMoreInteractions(orderRepository);
  }

  private Order createOrder() {
    Order order = new Order();
    order.setId(12345L);

    return order;
  }

  @Test
  public void testFindAll() {
    Order order = createOrder();

    when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));

    List<OrderResponse> orderResponses = orderService.findAll();

    Assert.assertNotNull(orderResponses);
    Assert.assertFalse(orderResponses.isEmpty());
    Assert.assertEquals(1, orderResponses.size());

    orderResponses.forEach(
        orderResponse -> Assert.assertEquals(order.getId(), orderResponse.getId()));

    verify(orderRepository).findAll();
  }
}
