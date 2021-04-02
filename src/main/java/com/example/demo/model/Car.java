package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Car {

  @Id
  @GeneratedValue
  private Long id;

  private String make;
  private int numberOfSeats;
  private CarType type;

  public Car() {}

  public Car(String make, int numberOfSeats, CarType type) {
    this.make = make;
    this.numberOfSeats = numberOfSeats;
    this.type = type;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public CarType getType() {
    return type;
  }

  public void setType(CarType type) {
    this.type = type;
  }
}
