package juamlosada.challengebesysoft.services;

import juamlosada.challengebesysoft.entities.Seller;

import java.util.List;

public interface SellerService {

    public Seller getById(Long id);

    public Seller save(Seller seller);

    public void delete(Seller seller);

    public void deleteById(Long id);

    public Seller update(Long id, Seller seller);

    public List<Seller> findAll();

    Double getCommision(Long id);
}
