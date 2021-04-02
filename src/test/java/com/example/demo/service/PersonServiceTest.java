package com.example.demo.service;

import com.example.demo.dto.person.request.CreatePersonDto;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @Spy
  private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

  @InjectMocks
  private PersonService personService;

  private final EasyRandom ezRandom = new EasyRandom();

  @Test
  public void shouldCreateAPerson() {
    var createPersonDto = new CreatePersonDto();
    createPersonDto.setName("Test name");
    var person = new Person(createPersonDto.getName());
    Mockito.when(personRepository.save(Mockito.any())).thenReturn(person);
    var result = personService.createPerson(createPersonDto);
    Assert.assertEquals(createPersonDto.getName(), result.getName());
  }

  @Test
  public void shouldListPersons() {
    var persons = ezRandom.objects(Person.class, 5).collect(Collectors.toList());
    Mockito.when(personRepository.findAll()).thenReturn(persons);
    var result = personService.listPersons();
    Assert.assertEquals(result.size(), persons.size());
  }
}
