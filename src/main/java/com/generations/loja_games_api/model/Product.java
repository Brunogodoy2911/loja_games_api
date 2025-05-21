package com.generations.loja_games_api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  @NotBlank(message = "O nome do produto é obrigatório")
  @Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres!")
  private String name;

  @Column(length = 1000)
  @NotBlank(message = "A descrição do produto é obrigatória")
  @Size(min = 10, max = 1000, message = "O atributo descrição deve ter no mínimo 10 e no máximo 1000 caracteres!")
  private String description;

  @Column(precision = 10, scale = 2)
  @NotNull(message = "O preço do produto é obrigatório")
  @DecimalMin(value = "0.00", inclusive = false, message = "O preço deve ser maior que zero")
  @Digits(integer = 8, fraction = 2, message = "O preço deve ter no máximo 8 dígitos inteiros e 2 decimais")
  private BigDecimal price;

  @Column(length = 5000)
  @NotBlank(message = "A imagem do produto é obrigatória")
  @Size(min = 10, max = 5000, message = "O atributo imagem deve ter no mínimo 10 e no máximo 5000 caracteres!")
  @Pattern(regexp = "^(https?|ftp)://.*$", message = "A URL da imagem deve ser válida")
  private String image;

  @UpdateTimestamp
  private LocalDateTime date;
}
