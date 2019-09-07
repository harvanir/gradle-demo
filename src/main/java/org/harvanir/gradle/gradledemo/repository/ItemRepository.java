package org.harvanir.gradle.gradledemo.repository;

import org.harvanir.gradle.gradledemo.entity.model.Item;
import org.harvanir.gradle.gradledemo.repository.support.QueryDslPredicateAndProjectionExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository
    extends JpaRepository<Item, Long>, QueryDslPredicateAndProjectionExecutor<Item> {}
