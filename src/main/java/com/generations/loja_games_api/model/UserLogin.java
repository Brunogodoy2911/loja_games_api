package com.generations.loja_games_api.model;

import lombok.Data;

@Data
public class UserLogin {

  private Long id;
  private String name;
  private String username;
  private String password;
  private String photo;
  private String token;
}