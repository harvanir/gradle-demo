package org.harvanir.gradle.gradledemo.controller.v1.order;

import org.harvanir.gradle.gradledemo.controller.v1.ApiPathV1;
import org.harvanir.gradle.gradledemo.entity.request.order.OrderCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.service.order.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RestController
@RequestMapping(ApiPathV1.V1_ORDERS)
public class OrderCommandController {

  private OrderCommandService orderCommandService;

  @Autowired
  public void setOrderCommandService(OrderCommandService orderCommandService) {
    this.orderCommandService = orderCommandService;
  }

  @PostMapping
  public OrderResponse create(@RequestBody OrderCreateRequest orderCreateRequest) {
    return orderCommandService.create(orderCreateRequest);
  }
}
