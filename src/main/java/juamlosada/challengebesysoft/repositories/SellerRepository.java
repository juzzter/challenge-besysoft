package juamlosada.challengebesysoft.repositories;

import juamlosada.challengebesysoft.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByName(String name);
}
