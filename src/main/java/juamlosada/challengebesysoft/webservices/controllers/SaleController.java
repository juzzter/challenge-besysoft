package juamlosada.challengebesysoft.webservices.controllers;

import juamlosada.challengebesysoft.configuration.exception.notfound.ProductNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SaleNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SellerNotFoundException;
import juamlosada.challengebesysoft.entities.Sale;
import juamlosada.challengebesysoft.services.SaleService;
import juamlosada.challengebesysoft.webservices.dto.category.SaleListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<SaleListDto> findAll() {
        return new ResponseEntity<>(SaleListDto.valueOf(saleService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sale sale) {
        try {
            return new ResponseEntity<>(saleService.save(sale), HttpStatus.CREATED);
        } catch (ProductNotFoundException | SellerNotFoundException e) {
            if (e instanceof ProductNotFoundException) {
                return new ResponseEntity<>("Product not found with id: " + sale.getProduct().getId(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Seller not found with id: " + sale.getSeller().getId(), HttpStatus.NOT_FOUND);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Sale sale) {
        try {
            return new ResponseEntity<>(saleService.update(id, sale), HttpStatus.OK);
        } catch (ProductNotFoundException | SellerNotFoundException | SaleNotFoundException e) {
            if (e instanceof ProductNotFoundException) {
                return new ResponseEntity<>("Product not found with id: " + sale.getProduct().getId(), HttpStatus.NOT_FOUND);
            } else if (e instanceof SellerNotFoundException) {
                return new ResponseEntity<>("Seller not found with id: " + sale.getSeller().getId(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Sale not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            saleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SaleNotFoundException e) {
            return new ResponseEntity<>("Sale not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
