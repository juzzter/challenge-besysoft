package juamlosada.challengebesysoft.services;

import juamlosada.challengebesysoft.entities.Sale;

import java.util.List;

public interface SaleService {

    public Sale getById(Long id);

    public Sale save(Sale sale);

    public void delete(Sale sale);

    public void deleteById(Long id);

    public Sale update(Long id, Sale sale);

    public List<Sale> findAll();

}
