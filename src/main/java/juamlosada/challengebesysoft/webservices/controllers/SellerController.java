package juamlosada.challengebesysoft.webservices.controllers;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateSellerException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SellerNotFoundException;
import juamlosada.challengebesysoft.entities.Seller;
import juamlosada.challengebesysoft.services.SellerService;
import juamlosada.challengebesysoft.webservices.dto.category.SellerListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<SellerListDto> findAll() {
        return new ResponseEntity<>(SellerListDto.valueOf(sellerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByid(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity<>("Seller not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Seller seller) {
        try {
            return new ResponseEntity<>(sellerService.save(seller), HttpStatus.CREATED);
        } catch (DuplicateSellerException e) {
            return new ResponseEntity<>("Seller already exists with name: " + seller.getName(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Seller seller) {
        try {
            return new ResponseEntity<>(sellerService.update(id, seller), HttpStatus.OK);
        } catch (DuplicateSellerException e) {
            return new ResponseEntity<>("Seller already exists with name: " + seller.getName(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            sellerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity<>("Seller not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/commission/{id}")
    public ResponseEntity<?> getCommision(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(sellerService.getCommision(id), HttpStatus.OK);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity<>("Seller not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
