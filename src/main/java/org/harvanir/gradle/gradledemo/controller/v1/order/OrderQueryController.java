package org.harvanir.gradle.gradledemo.controller.v1.order;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.harvanir.gradle.gradledemo.controller.v1.ApiPathV1;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.harvanir.gradle.gradledemo.exception.NotFoundException;
import org.harvanir.gradle.gradledemo.service.order.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RestController
@RequestMapping(ApiPathV1.V1_ORDERS)
public class OrderQueryController {

  private OrderQueryService orderQueryService;

  @Autowired
  public void setOrderQueryService(OrderQueryService orderQueryService) {
    this.orderQueryService = orderQueryService;
  }

  @GetMapping("/id/{id}")
  public OrderResponse findById(@PathVariable @Valid @NotNull Long id) {
    return orderQueryService
        .findById(id)
        .orElseGet(
            () -> {
              throw new NotFoundException("Order not found");
            });
  }

  @GetMapping
  public List<OrderResponse> findAll() {
    return orderQueryService.findAll();
  }
}
