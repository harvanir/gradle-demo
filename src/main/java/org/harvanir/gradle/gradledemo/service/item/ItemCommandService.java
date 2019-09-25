package org.harvanir.gradle.gradledemo.service.item;

import org.harvanir.gradle.gradledemo.entity.request.item.CreateItemRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;

/** @author Harvan Irsyadi */
public interface ItemCommandService {

  ItemResponse create(CreateItemRequest createItemRequest);

  ItemResponse increase(Long id, int decrement);
}
