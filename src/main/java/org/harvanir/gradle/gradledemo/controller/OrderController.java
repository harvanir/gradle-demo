package org.harvanir.gradle.gradledemo.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.harvanir.gradle.gradledemo.entity.request.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.OrderResponse;
import org.harvanir.gradle.gradledemo.exception.NotFoundException;
import org.harvanir.gradle.gradledemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

  private OrderService orderService;

  @Autowired
  public void setOrderService(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public OrderResponse create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
    return orderService.create(createOrderRequest);
  }

  @GetMapping("/id/{id}")
  public OrderResponse findById(@PathVariable @Valid @NotNull Long id) {
    return orderService
        .findById(id)
        .orElseGet(
            () -> {
              throw new NotFoundException("Order not found");
            });
  }

  @GetMapping
  public List<OrderResponse> findAll() {
    return orderService.findAll();
  }
}
