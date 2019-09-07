package org.harvanir.gradle.gradledemo.entity;

import org.mapstruct.factory.Mappers;

public class EntityMapper {

  public static final EntityBeanMapper MAPPER = Mappers.getMapper(EntityBeanMapper.class);

  private EntityMapper() {}
}
