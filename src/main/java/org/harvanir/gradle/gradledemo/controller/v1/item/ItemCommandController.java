package org.harvanir.gradle.gradledemo.controller.v1.item;

import org.harvanir.gradle.gradledemo.controller.v1.ApiPathV1;
import org.harvanir.gradle.gradledemo.entity.request.item.ItemCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.harvanir.gradle.gradledemo.service.item.ItemCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemCommandController {

  private ItemCommandService itemCommandService;

  @Autowired
  public void setItemCommandService(ItemCommandService itemCommandService) {
    this.itemCommandService = itemCommandService;
  }

  @PostMapping
  public ItemResponse create(@RequestBody ItemCreateRequest itemCreateRequest) {
    return itemCommandService.create(itemCreateRequest);
  }

  @PutMapping("/{id}/increase")
  public ItemResponse increase(@PathVariable Long id) {
    return itemCommandService.increase(id, 1);
  }
}
