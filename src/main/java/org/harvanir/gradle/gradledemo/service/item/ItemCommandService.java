package org.harvanir.gradle.gradledemo.service.item;

import org.harvanir.gradle.gradledemo.entity.request.item.ItemCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;

/** @author Harvan Irsyadi */
public interface ItemCommandService {

  ItemResponse create(ItemCreateRequest itemCreateRequest);

  ItemResponse increase(Long id, int decrement);
}
