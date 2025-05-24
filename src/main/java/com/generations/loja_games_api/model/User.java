package com.generations.loja_games_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome do usuário é obrigatório")
  private String name;

  @NotBlank(message = "O Atributo Usuário é Obrigatório!")
  @Email(message = "O Atributo Usuário deve ser um email válido!")
  private String username;

  @NotBlank(message = "A senha do usuário é obrigatória")
  @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
  private String password;

  @Size(max = 5000, message = "O atributo imagem deve ter no máximo 5000 caracteres")
  private String photo;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoto() {
    return this.photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
