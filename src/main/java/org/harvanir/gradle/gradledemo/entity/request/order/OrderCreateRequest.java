package org.harvanir.gradle.gradledemo.entity.request.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import lombok.*;

/** @author Harvan Irsyadi */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateRequest {

  private OrderStatusEnum status;

  private Set<OrderItemCreateRequest> items;
}
