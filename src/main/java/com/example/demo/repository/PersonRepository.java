package com.example.demo.repository;

import com.example.demo.model.Person;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends Neo4jRepository<Person, Long> {
  @Query("MATCH (n:Person) where n.name='Greg' return n;")
  Person findGreg();

  Person findByName(String name);
  List<Person> findByTeammatesName(String name);
}
