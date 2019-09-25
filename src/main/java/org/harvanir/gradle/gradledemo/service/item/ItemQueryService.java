package org.harvanir.gradle.gradledemo.service.item;

import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemQueryService {

  Iterable<ItemResponse> findAll();

  Page<ItemResponse> findAll(Pageable pageable);

  ItemResponse findById(Long id);
}
