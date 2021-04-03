package com.example.demo.dto.person.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePersonDto {

  @NotBlank(message = "Name can not be empty")
  private String name;
}
