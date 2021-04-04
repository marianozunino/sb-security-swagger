package com.example.demo;

import com.example.demo.model.Car;
import com.example.demo.model.CarType;
import com.example.demo.model.Person;
import com.example.demo.model.UserContext;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.PersonRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Sample Spring Boot API",
    version = "v1",
    description = "A demo project using Spring Boot with Swagger-UI enabled"
  )
)
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@EnableNeo4jAuditing(modifyOnCreate = true, setDates = true)
public class DemoApplication {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  @RequestScope
  public UserContext requestUserDataContext() {
    return new UserContext();
  }

  @Bean
  CommandLineRunner demo(PersonRepository personRepository, CarRepository carRepository) {
    return args -> {
      personRepository.deleteAll();
      carRepository.deleteAll();

      var greg = new Person("Greg");
      var roy = new Person("Roy");
      var craig = new Person("Craig");

      personRepository.save(greg);
      personRepository.save(roy);
      personRepository.save(craig);

      var x = personRepository.findGreg();
      log.info("Este es GREG... {}", x.getCreatedAt());

      greg.worksWith(roy);
      personRepository.save(greg);

      var auto = new Car("Nose que", 4, CarType.SEDAN);
      carRepository.save(auto);
      greg.addCar(auto);
      personRepository.save(greg);
    };
  }
}
