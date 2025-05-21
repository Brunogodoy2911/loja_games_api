package com.generations.loja_games_api.controller;

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

import com.generations.loja_games_api.model.Category;
import com.generations.loja_games_api.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(categoryRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getById(@PathVariable Long id) {
    return categoryRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Category>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(categoryRepository.findAllByNameContainingIgnoreCase(name));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<Category>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(categoryRepository.findAllByDescriptionContainingIgnoreCase(description));
  }

  @PostMapping
  public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
    Category savedCategory = categoryRepository.save(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping
  public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
    if (category.getId() == null)
      return ResponseEntity.notFound().build();

    return categoryRepository.findById(category.getId())
        .map(existingCategory -> ResponseEntity.ok(categoryRepository.save(category)))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {

    if (!categoryRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
    }

    categoryRepository.deleteById(id);
  }

}
