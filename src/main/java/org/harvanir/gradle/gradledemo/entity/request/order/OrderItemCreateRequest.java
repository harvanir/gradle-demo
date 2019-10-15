package org.harvanir.gradle.gradledemo.entity.request.order;

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
public class OrderItemCreateRequest {

  @NotNull private Long id;

  @NotNull private Integer quantity;

  @NotNull private BigDecimal price;
}
