package org.harvanir.gradle.gradledemo.service;

import org.harvanir.gradle.gradledemo.entity.request.CreateItemRequest;
import org.harvanir.gradle.gradledemo.entity.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemService {

  Iterable<ItemResponse> findAll();

  Page<ItemResponse> findAll(Pageable pageable);

  ItemResponse create(CreateItemRequest createItemRequest);

  ItemResponse findById(Long id);

  ItemResponse increase(Long id, int decrement);
}
