package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateProductException;
import juamlosada.challengebesysoft.configuration.exception.notfound.CategoryNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.ProductNotFoundException;
import juamlosada.challengebesysoft.entities.Category;
import juamlosada.challengebesysoft.entities.Product;
import juamlosada.challengebesysoft.repositories.CategoryRepository;
import juamlosada.challengebesysoft.repositories.ProductRepository;
import juamlosada.challengebesysoft.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(productRepository.existsByName(product.getName())) {
            throw new DuplicateProductException(product.getName());
        }

        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId()));

        product.setCategory(category);

        category.getProducts().add(product);

        return productRepository.save(product);

    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {

        Product prod = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        if(productRepository.existsByName(product.getName()) && !Objects.equals(prod.getName(), product.getName())) {
            throw new DuplicateProductException(product.getName());
        }


        Category categoryNew = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId()));


        if (!categoryNew.getProducts().contains(prod)) {
            categoryNew.getProducts().add(prod);
            prod.getCategory().getProducts().remove(prod);
        }

        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        prod.setCategory(categoryNew);
        prod.setSales(product.getSales());

        return productRepository.save(prod);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategoryId(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }

        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> findByPrice(Double price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> findByStock(Integer stock) {
        return productRepository.findByStock(stock);
    }


}
