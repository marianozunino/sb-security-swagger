package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.util.JwtUtil;
import java.util.Optional;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

  @Mock
  private PersonRepository personRepository;

  @Spy
  private final JwtUtil jwtUtil = new JwtUtil("SuperSecretKey");

  @InjectMocks
  private AuthService authService;

  private final EasyRandom ezRandom = new EasyRandom();

  @Test
  public void shouldDoLogin() throws ApiException {
    EasyRandomParameters parameters = new EasyRandomParameters().excludeField(FieldPredicates.named("teammates"));
    var ezRandom = new EasyRandom(parameters);
    var person = ezRandom.nextObject(Person.class);
    Mockito.when(this.personRepository.findByName("Greg")).thenReturn(Optional.of(person));
    var token = this.authService.login("token");
    Assert.assertNotNull(token);
  }
}
