package com.example.demo.mapper;

import com.example.demo.dto.person.request.CreatePersonDto;
import com.example.demo.dto.person.response.ReadPersonDto;
import com.example.demo.model.Person;
import java.util.Date;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMapper {
  Person createPersonDtoToPerson(CreatePersonDto createPersonDto);

  ReadPersonDto personToReadPersonDto(Person person);

  default Date map(long value) {
    return new Date(value);
  }
}
