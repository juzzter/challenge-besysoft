package juamlosada.challengebesysoft.webservices.controllers;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateCategoryException;
import juamlosada.challengebesysoft.configuration.exception.notfound.CategoryNotFoundException;
import juamlosada.challengebesysoft.entities.Category;
import juamlosada.challengebesysoft.services.CategoryService;
import juamlosada.challengebesysoft.webservices.dto.category.CategoryListDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDto> findAll(){
        return new ResponseEntity<>(CategoryListDto.valueOf(categoryService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
        }
        catch (CategoryNotFoundException e) {
            return new ResponseEntity<>("Category not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
        try {
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
        } catch (DuplicateCategoryException e) {
            return new ResponseEntity<>("Category already exists with name: " + category.getName(), HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){
        try {
            return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
        }catch (DuplicateCategoryException e) {
            return new ResponseEntity<>("Category already exists with name: " + category.getName(), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (CategoryNotFoundException e) {
            return new ResponseEntity<>("Category not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
