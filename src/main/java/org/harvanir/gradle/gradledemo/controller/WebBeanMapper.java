package org.harvanir.gradle.gradledemo.controller;

import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper
public interface WebBeanMapper {

  OrderResponse mapFromModel(Order order);
}
