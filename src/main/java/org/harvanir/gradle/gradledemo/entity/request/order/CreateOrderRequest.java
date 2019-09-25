package org.harvanir.gradle.gradledemo.entity.request.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.harvanir.gradle.gradledemo.entity.request.item.CreateItemRequest;

/** @author Harvan Irsyadi */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequest {

  private OrderStatusEnum orderStatusEnum;

  private Set<CreateItemRequest> items;
}
