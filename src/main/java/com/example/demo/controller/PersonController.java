package com.example.demo.controller;

import com.example.demo.dto.person.request.CreatePersonDto;
import com.example.demo.dto.person.response.ReadPersonDto;
import com.example.demo.model.UserContext;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persons")
//@SecurityRequirement(name = "bearerAuth") // Requires Auth!!!
@Validated
public class PersonController {

  @Resource(name = "requestUserDataContext")
  private UserContext userContext;

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  @Operation(summary = "Este endpoint lista personas")
  ResponseEntity<List<ReadPersonDto>> listPersons(@RequestParam("age") @Min(5) int age) {
    return ResponseEntity.ok(this.personService.listPersons());
  }

  @PostMapping
  @Operation(summary = "Este endpoint crea y retorna una persona")
  ResponseEntity<ReadPersonDto> createPerson(@RequestBody @Valid CreatePersonDto createPersonDto) {
    var persistedPerson = this.personService.createPerson(createPersonDto);
    return ResponseEntity.ok(persistedPerson);
  }
}
