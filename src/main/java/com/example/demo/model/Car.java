package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;

@EqualsAndHashCode(callSuper = true)
@Node
@Data
@NoArgsConstructor
public class Car extends BaseEntity {

  private String make;
  private int numberOfSeats;
  private CarType type;

  public Car(String make, int numberOfSeats, CarType type) {
    this.make = make;
    this.numberOfSeats = numberOfSeats;
    this.type = type;
  }
}
