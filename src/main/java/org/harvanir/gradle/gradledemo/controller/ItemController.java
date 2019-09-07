package org.harvanir.gradle.gradledemo.controller;

import javax.validation.Valid;
import org.harvanir.gradle.gradledemo.entity.request.CreateItemRequest;
import org.harvanir.gradle.gradledemo.entity.response.ItemResponse;
import org.harvanir.gradle.gradledemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping("/v1/items")
@RestController
public class ItemController {

  private ItemService itemService;

  @Autowired
  private void setItemService(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping
  public ItemResponse create(@Valid @RequestBody CreateItemRequest createItemRequest) {
    return itemService.create(createItemRequest);
  }

  @GetMapping
  public Page<ItemResponse> findAll(Pageable pageable) {
    return itemService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ItemResponse find(@PathVariable Long id) {
    return itemService.findById(id);
  }
}
