package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.entities.Sale;
import juamlosada.challengebesysoft.repositories.SaleRepository;
import juamlosada.challengebesysoft.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale getById(Long id) {
        return null;
    }

    @Override
    public Sale save(Sale sale) {
        return null;
    }

    @Override
    public void delete(Sale sale) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Sale update(Long id, Sale sale) {
        return null;
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }
}
