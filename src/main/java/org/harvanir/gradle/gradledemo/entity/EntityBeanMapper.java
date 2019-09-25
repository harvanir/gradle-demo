package org.harvanir.gradle.gradledemo.entity;

import org.harvanir.gradle.gradledemo.entity.model.Item;
import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.request.item.CreateItemRequest;
import org.harvanir.gradle.gradledemo.entity.request.order.CreateOrderRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EntityBeanMapper {

  OrderResponse toDto(Order order);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderItems", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "statusCode", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "version", ignore = true)
  Order toEntity(CreateOrderRequest createOrderRequest);

  ItemResponse toDto(Item item);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderItems", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "version", ignore = true)
  Item toEntity(CreateItemRequest createItemRequest);
}
