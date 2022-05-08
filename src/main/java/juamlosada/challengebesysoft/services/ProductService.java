package juamlosada.challengebesysoft.services;

import juamlosada.challengebesysoft.entities.Product;

import java.util.List;

public interface ProductService {

    public Product getById(Long id);

    public Product save(Product product);

    public void delete(Product product);

    public void deleteById(Long id);

    public Product update(Long id, Product product);

    public List<Product> findAll();

    List<Product> findByCategoryId(Long id);

    List<Product> findByPrice(Double price);

    Object findByStock(Integer stock);
}
