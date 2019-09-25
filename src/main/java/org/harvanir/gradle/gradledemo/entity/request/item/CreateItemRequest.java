package org.harvanir.gradle.gradledemo.entity.request.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** @author Harvan Irsyadi */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateItemRequest {

  @NotNull private String name;

  @NotNull private Integer quantity;

  @NotNull private BigDecimal price;
}
