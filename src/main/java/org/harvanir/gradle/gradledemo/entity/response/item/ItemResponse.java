package org.harvanir.gradle.gradledemo.entity.response.item;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemResponse {

  private Long id;

  private String name;

  private Integer quantity;

  private BigDecimal price;

  private Date createdAt;

  private Date updatedAt;
}
