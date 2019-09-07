package org.harvanir.gradle.gradledemo.repository;

import org.harvanir.gradle.gradledemo.entity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository
    extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {}
