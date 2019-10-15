package org.harvanir.gradle.gradledemo.service.item.impl;

import org.harvanir.gradle.gradledemo.entity.EntityBeanMapper;
import org.harvanir.gradle.gradledemo.entity.model.Item;
import org.harvanir.gradle.gradledemo.entity.request.item.ItemCreateRequest;
import org.harvanir.gradle.gradledemo.entity.response.item.ItemResponse;
import org.harvanir.gradle.gradledemo.repository.ItemRepository;
import org.harvanir.gradle.gradledemo.service.item.ItemCommandService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/** @author Harvan Irsyadi */
@Service
public class ItemCommandServiceImpl implements ItemCommandService {

  private ItemRepository itemRepository;

  private EntityBeanMapper mapper;

  @Autowired
  public void setItemRepository(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Autowired
  public void setMapper(EntityBeanMapper mapper) {
    this.mapper = mapper;
  }

  @Validated
  @Override
  public ItemResponse create(@Validated ItemCreateRequest itemCreateRequest) {
    return mapper.toDto(itemRepository.save(mapper.toEntity(itemCreateRequest)));
  }

  @Retryable(
      value = {StaleObjectStateException.class, ObjectOptimisticLockingFailureException.class},
      maxAttemptsExpression = "${app.retry.max-attempts}",
      backoff = @Backoff(delayExpression = "${app.retry.delay}"))
  @Transactional
  @Override
  public ItemResponse increase(Long id, int increment) {
    Item item = itemRepository.getOne(id);
    item.setQuantity(item.getQuantity() + increment);

    return mapper.toDto(itemRepository.save(item));
  }
}
