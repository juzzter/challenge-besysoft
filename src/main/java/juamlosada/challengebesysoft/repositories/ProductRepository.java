package juamlosada.challengebesysoft.repositories;

import juamlosada.challengebesysoft.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    List<Product> findByCategoryId(Long id);

    List<Product> findByPrice(Double price);

    List<Product> findByStock(Integer stock);
}
