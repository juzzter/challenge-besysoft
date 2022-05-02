package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.configuration.exception.notfound.ProductNotFoundException;
import juamlosada.challengebesysoft.entities.Product;
import juamlosada.challengebesysoft.repositories.CategoryRepository;
import juamlosada.challengebesysoft.repositories.ProductRepository;
import juamlosada.challengebesysoft.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {

        Product prod = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        prod.setCategory(product.getCategory());

        return productRepository.save(prod);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
