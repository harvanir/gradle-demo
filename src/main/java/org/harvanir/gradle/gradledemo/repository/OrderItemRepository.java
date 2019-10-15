package org.harvanir.gradle.gradledemo.repository;

import org.harvanir.gradle.gradledemo.entity.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/** @author Harvan Irsyadi */
public interface OrderItemRepository
    extends JpaRepository<OrderItem, Long>, QuerydslPredicateExecutor<OrderItem> {}
