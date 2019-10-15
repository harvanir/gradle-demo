package org.harvanir.gradle.gradledemo.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.harvanir.gradle.gradledemo.entity.model.Item;
import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.model.OrderItem;
import org.harvanir.gradle.gradledemo.entity.request.item.ItemCreateRequest;
import org.harvanir.gradle.gradledemo.entity.request.order.OrderCreateRequest;
import org.harvanir.gradle.gradledemo.entity.request.order.OrderItemCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public abstract class EntityBeanMapper {

  @PersistenceContext private EntityManager entityManager;

  public abstract OrderResponse toDto(Order order);

  @Mapping(target = "statusCode", source = "status.code")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "items", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "version", ignore = true)
  public abstract Order toEntity(OrderCreateRequest orderCreateRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "item", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "price", source = "orderItemCreateRequest.price")
  @Mapping(target = "quantity", source = "orderItemCreateRequest.quantity")
  public abstract OrderItem toEntity(Order order, OrderItemCreateRequest orderItemCreateRequest);

  @AfterMapping
  void attachItem(
      OrderItemCreateRequest orderItemCreateRequest, @MappingTarget OrderItem orderItem) {
    orderItem.setItem(entityManager.getReference(Item.class, orderItemCreateRequest.getId()));
  }

  public Set<OrderItem> toEntity(Order order, Set<OrderItemCreateRequest> items) {
    if (items == null) {
      return Collections.emptySet();
    }

    Set<OrderItem> set = new HashSet<>(Math.max((int) (items.size() / .75f) + 1, 16));
    for (OrderItemCreateRequest orderItemCreateRequest : items) {
      set.add(toEntity(order, orderItemCreateRequest));
    }

    return set;
  }

  public abstract ItemResponse toDto(Item item);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderItems", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "version", ignore = true)
  public abstract Item toEntity(ItemCreateRequest itemCreateRequest);
}
