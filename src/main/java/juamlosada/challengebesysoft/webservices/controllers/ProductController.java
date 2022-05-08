package juamlosada.challengebesysoft.webservices.controllers;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateProductException;
import juamlosada.challengebesysoft.configuration.exception.notfound.CategoryNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.ProductNotFoundException;
import juamlosada.challengebesysoft.entities.Product;
import juamlosada.challengebesysoft.services.ProductService;
import juamlosada.challengebesysoft.webservices.dto.category.ProductListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductListDto> findAll() {
        return new ResponseEntity<>(ProductListDto.valueOf(productService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        } catch (DuplicateProductException | CategoryNotFoundException e) {
            if (e instanceof DuplicateProductException) {
                return new ResponseEntity<>("Product already exists with name: " + product.getName(), HttpStatus.CONFLICT);
            }else {
                return new ResponseEntity<>("Category not found with id: " + product.getCategory().getId(), HttpStatus.NOT_FOUND);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
        } catch (DuplicateProductException | ProductNotFoundException | CategoryNotFoundException e) {
            if (e instanceof DuplicateProductException) {
                return new ResponseEntity<>("Product already exists with name: " + product.getName(), HttpStatus.CONFLICT);
            } else if (e instanceof ProductNotFoundException) {
                return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Category not found with id: " + product.getCategory().getId(), HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(ProductListDto.valueOf(productService.findByCategoryId(id)), HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>("Category not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<?> findByPrice(@PathVariable Double price) {
        return new ResponseEntity<>(productService.findByPrice(price), HttpStatus.OK);
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<?> findByStock(@PathVariable Integer stock) {
        return new ResponseEntity<>(productService.findByStock(stock), HttpStatus.OK);
    }

    @GetMapping("/not-stock")
    public ResponseEntity<?> findByNotStock() {
        return new ResponseEntity<>(productService.findByStock(0), HttpStatus.OK);
    }

}
