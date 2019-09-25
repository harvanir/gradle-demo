package org.harvanir.gradle.gradledemo.service.order;

import java.util.List;
import java.util.Optional;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;

public interface OrderQueryService {

  List<OrderResponse> findAll();

  Optional<OrderResponse> findById(Long id);
}
