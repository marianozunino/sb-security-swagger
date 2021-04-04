package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@EqualsAndHashCode(callSuper = true)
@Node
@Data
@NoArgsConstructor
public class Person extends BaseEntity {

  private String name;

  @Relationship(type = "TEAMMATE", direction = Relationship.Direction.INCOMING)
  private Set<Person> teammates;

  @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
  public Set<Car> cars;

  public Person(String name) {
    this.name = name;
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
