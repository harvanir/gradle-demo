package org.harvanir.gradle.gradledemo.service.order;

import org.harvanir.gradle.gradledemo.entity.request.order.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;

public interface OrderCommandService {

  OrderResponse create(CreateOrderRequest createOrderRequest);
}
