package com.generations.loja_games_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres!")
  private String name;

  @Column(length = 1000)
  @NotBlank(message = "A descrição da categoria é obrigatória")
  @Size(min = 10, max = 1000, message = "O atributo descrição deve ter no mínimo 10 e no máximo 1000 caracteres!")
  private String description;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("category")
  private List<Product> products;

}
