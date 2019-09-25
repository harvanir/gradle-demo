package org.harvanir.gradle.gradledemo.controller.v1.item;

import org.harvanir.gradle.gradledemo.controller.v1.ApiPathV1;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.harvanir.gradle.gradledemo.service.item.ItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemQueryController {

  private ItemQueryService itemQueryService;

  @Autowired
  private void setItemQueryService(ItemQueryService itemQueryService) {
    this.itemQueryService = itemQueryService;
  }

  @GetMapping
  public Page<ItemResponse> findAll(Pageable pageable) {
    return itemQueryService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ItemResponse find(@PathVariable Long id) {
    return itemQueryService.findById(id);
  }
}
