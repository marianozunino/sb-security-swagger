package com.example.demo.controller;

import com.example.demo.dto.person.request.CreatePersonDto;
import com.example.demo.dto.person.response.ReadPersonDto;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persons")
@SecurityRequirement(name = "bearerAuth")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  @Operation(summary = "Este endpoint lista personas")
  ResponseEntity<List<ReadPersonDto>> listPersons() {
    return ResponseEntity.ok(this.personService.listPersons());
  }

  @PostMapping
  @Operation(summary = "Este endpoint crea y retorna una persona")
  ResponseEntity<ReadPersonDto> createPerson(@RequestBody CreatePersonDto createPersonDto) {
    var persistedPerson = this.personService.createPerson(createPersonDto);
    return ResponseEntity.ok(persistedPerson);
  }
}
