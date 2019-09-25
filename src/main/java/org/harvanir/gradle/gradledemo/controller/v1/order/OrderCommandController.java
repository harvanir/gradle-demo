package org.harvanir.gradle.gradledemo.controller.v1.order;

import javax.validation.Valid;
import org.harvanir.gradle.gradledemo.controller.v1.ApiPathV1;
import org.harvanir.gradle.gradledemo.entity.request.order.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.service.order.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathV1.V1_ORDERS)
public class OrderCommandController {

  private OrderCommandService orderCommandService;

  @Autowired
  public void setOrderCommandService(OrderCommandService orderCommandService) {
    this.orderCommandService = orderCommandService;
  }

  @PostMapping
  public OrderResponse create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
    return orderCommandService.create(createOrderRequest);
  }
}
