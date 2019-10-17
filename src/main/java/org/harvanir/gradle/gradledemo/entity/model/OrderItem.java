package org.harvanir.gradle.gradledemo.entity.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** @author Harvan Irsyadi */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = TableConstant.ORDER_ITEMS)
public class OrderItem {

  private static final String SEQ_GENERATORS = "order_item_generators";

  private static final String SEQ_NAME = "seq_order_items";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATORS)
  @SequenceGenerator(name = SEQ_GENERATORS, sequenceName = SEQ_NAME)
  @Column
  private Long id;

  @Column private Integer quantity;

  @Column private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @CreatedDate @Column private Date createdAt;

  @LastModifiedDate @Column private Date updatedAt;

  @Version @Column private Integer version;
}
