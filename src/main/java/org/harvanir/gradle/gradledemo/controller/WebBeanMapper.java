package org.harvanir.gradle.gradledemo.controller;

import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.harvanir.gradle.gradledemo.entity.response.order.OrderResponse;
import org.mapstruct.Mapper;

/** @author Harvan Irsyadi */
@Mapper
public interface WebBeanMapper {

  OrderResponse mapFromModel(Order order);
}
