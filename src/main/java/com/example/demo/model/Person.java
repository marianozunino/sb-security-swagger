package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Relationship(type = "TEAMMATE", direction = Relationship.Direction.INCOMING)
  public Set<Person> teammates;

  @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
  public Set<Car> cars;

  public Person() {}

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Persona{" + "id=" + id + ", name='" + name + '\'' + ", teammates=" + teammates + ", cars=" + cars + '}';
  }
}
