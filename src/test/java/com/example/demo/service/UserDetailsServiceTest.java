package com.example.demo.service;

import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

  @InjectMocks
  private MyUserDetailsService userDetailsService;

  private final EasyRandom ezRandom = new EasyRandom();

  @Test
  public void shouldCreateAPerson() {
    var result = userDetailsService.loadUserByUsername("foo");
    Assert.assertEquals("foo", result.getUsername());
  }
}
