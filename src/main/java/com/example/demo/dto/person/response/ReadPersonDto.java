package com.example.demo.dto.person.response;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadPersonDto {

  private String name;
  private Date createdAt;
  private Date updatedAt;
}
