package org.harvanir.gradle.gradledemo.entity.request;

/** @author Harvan Irsyadi */
public enum OrderStatusEnum {
  PENDING(0),
  PROCESSING(1),
  CANCELLED(2),
  COMPLETED(3),
  DELIVERED(4);

  private int code;

  OrderStatusEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String toString() {
    return String.valueOf(code);
  }
}
