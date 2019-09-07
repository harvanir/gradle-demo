package org.harvanir.gradle.gradledemo.service;

import java.util.List;
import java.util.Optional;
import org.harvanir.gradle.gradledemo.entity.request.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.OrderResponse;

public interface OrderService {

  OrderResponse create(CreateOrderRequest createOrderRequest);

  List<OrderResponse> findAll();

  Optional<OrderResponse> findById(Long id);
}
