package org.harvanir.gradle.gradledemo.service.impl;

import static org.harvanir.gradle.gradledemo.entity.EntityMapper.MAPPER;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import java.util.List;
import org.harvanir.gradle.gradledemo.entity.model.QItem;
import org.harvanir.gradle.gradledemo.entity.request.CreateItemRequest;
import org.harvanir.gradle.gradledemo.entity.response.ItemResponse;
import org.harvanir.gradle.gradledemo.repository.ItemRepository;
import org.harvanir.gradle.gradledemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** @author Harvan Irsyadi */
@Service
public class ItemServiceImpl implements ItemService {

  private ItemRepository itemRepository;

  @Autowired
  public void setItemRepository(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public ItemResponse create(CreateItemRequest createItemRequest) {
    return MAPPER.toDto(itemRepository.save(MAPPER.toEntity(createItemRequest)));
  }

  private QItem getItem() {
    return QItem.item;
  }

  private QBean<ItemResponse> getExpressionBase() {
    QItem item = getItem();

    return Projections.bean(
        ItemResponse.class,
        item.id,
        item.name,
        item.price,
        item.quantity,
        item.createdAt,
        item.updatedAt);
  }

  @Override
  public List<ItemResponse> findAll() {
    return itemRepository.findAll(getExpressionBase());
  }

  @Override
  public Page<ItemResponse> findAll(Pageable pageable) {
    return itemRepository.findAll(getExpressionBase(), pageable);
  }

  @Override
  public ItemResponse findById(Long id) {
    return itemRepository.findOne(getExpressionBase(), getItem().id.eq(id));
  }
}
