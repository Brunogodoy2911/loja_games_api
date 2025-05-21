package com.generations.loja_games_api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generations.loja_games_api.model.Product;
import com.generations.loja_games_api.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    return ResponseEntity.ok(productRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    return productRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(productRepository.findAllByNameContainingIgnoreCase(name));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<Product>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(productRepository.findAllByDescriptionContainingIgnoreCase(description));
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<Product>> getByPrice(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productRepository.findAllByPrice(price));
  }

  @PostMapping
  public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
  }

  @PutMapping
  public ResponseEntity<Product> update(@Valid @RequestBody Product product) {

    if (product.getId() == null)
      return ResponseEntity.notFound().build();

    return productRepository.findById(product.getId())
        .map(existingProduct -> ResponseEntity.ok(productRepository.save(product)))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {

    if (!productRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    productRepository.deleteById(id);
  }

}
