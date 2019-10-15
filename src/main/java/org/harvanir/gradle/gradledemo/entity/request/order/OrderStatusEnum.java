package org.harvanir.gradle.gradledemo.entity.request.order;

/** @author Harvan Irsyadi */
public enum OrderStatusEnum {
  PENDING((short) 0),
  PROCESSING((short) 1),
  CANCELLED((short) 2),
  COMPLETED((short) 3),
  DELIVERED((short) 4);

  private short code;

  OrderStatusEnum(short code) {
    this.code = code;
  }

  public short getCode() {
    return code;
  }

  @Override
  public String toString() {
    return String.valueOf(code);
  }
}
