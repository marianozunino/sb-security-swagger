package com.example.demo.service;

import com.example.demo.dto.person.request.CreatePersonDto;
import com.example.demo.dto.person.response.ReadPersonDto;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  public ReadPersonDto createPerson(CreatePersonDto createPersonDto) {
    var a = this.personMapper.createPersonDtoToPerson(createPersonDto);
    var b = this.personRepository.save(a);
    return this.personMapper.personToReadPersonDto(b);
  }

  public List<ReadPersonDto> listPersons() {
    var persons = this.personRepository.findAll();
    return persons.stream().map(this.personMapper::personToReadPersonDto).collect(Collectors.toList());
  }
}
