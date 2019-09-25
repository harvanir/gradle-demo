package org.harvanir.gradle.gradledemo.entity.response.order;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

  private Long id;

  private String status;

  private Short statusCode;

  private Date createdAt;

  private Date updatedAt;
}
