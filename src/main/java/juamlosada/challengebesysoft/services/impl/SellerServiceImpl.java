package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateSellerException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SellerNotFoundException;
import juamlosada.challengebesysoft.entities.Sale;
import juamlosada.challengebesysoft.entities.Seller;
import juamlosada.challengebesysoft.repositories.SellerRepository;
import juamlosada.challengebesysoft.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller getById(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
    }

    @Override
    public Seller save(Seller seller) {

        if(sellerRepository.existsByName(seller.getName())) {
            throw new DuplicateSellerException(seller.getName());
        }
        return sellerRepository.save(seller);
    }

    @Override
    public void delete(Seller seller) {
        sellerRepository.delete(seller);
    }

    @Override
    public void deleteById(Long id) {
        if (!sellerRepository.existsById(id)) {
            throw new SellerNotFoundException(id);
        }
        sellerRepository.deleteById(id);
    }

    @Override
    public Seller update(Long id, Seller seller) {

        if(sellerRepository.existsByName(seller.getName())) {
            throw new DuplicateSellerException(seller.getName());
        }

        Seller sell = sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));

        sell.setName(seller.getName());
        sell.setSalary(seller.getSalary());
        sell.setSales(seller.getSales());

        return sellerRepository.save(sell);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Double getCommision(Long id) {
        if (!sellerRepository.existsById(id)) {
            throw new SellerNotFoundException(id);
        }

        int soldProducts = 0;
        double totalSales = (double) 0;

        for (Sale sale : sellerRepository.findById(id).get().getSales()) {
            soldProducts += sale.getQuantity();
            totalSales += sale.getProduct().getPrice() * sale.getQuantity();
        }

        double commision = (double) 0;

        if (soldProducts <= 2) {
            commision = totalSales * 0.05;
        }else {
            commision = totalSales * 0.1;
        }

        return commision;
    }
}
