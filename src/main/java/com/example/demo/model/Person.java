package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private long createdAt;
  private long updatedAt;
  private long deletedAt;

  @Relationship(type = "TEAMMATE", direction = Relationship.Direction.INCOMING)
  private Set<Person> teammates;

  @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
  public Set<Car> cars;

  public Person(String name) {
    this.name = name;
    this.createdAt = new Date().getTime();
    this.updatedAt = new Date().getTime();
  }

  public void worksWith(Person person) {
    if (teammates == null) {
      teammates = new HashSet<>();
    }
    teammates.add(person);
  }

  public void addCar(Car car) {
    if (cars == null) {
      cars = new HashSet<>();
    }
    cars.add(car);
  }
}
