package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.configuration.exception.notfound.ProductNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SaleNotFoundException;
import juamlosada.challengebesysoft.configuration.exception.notfound.SellerNotFoundException;
import juamlosada.challengebesysoft.entities.Product;
import juamlosada.challengebesysoft.entities.Sale;
import juamlosada.challengebesysoft.entities.Seller;
import juamlosada.challengebesysoft.repositories.ProductRepository;
import juamlosada.challengebesysoft.repositories.SaleRepository;
import juamlosada.challengebesysoft.repositories.SellerRepository;
import juamlosada.challengebesysoft.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Sale getById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException(id));
    }

    @Override
    public Sale save(Sale sale) {

        Product product = productRepository.findById(sale.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException(sale.getProduct().getId()));

        Seller seller = sellerRepository.findById(sale.getSeller().getId()).orElseThrow(() -> new SellerNotFoundException(sale.getSeller().getId()));

        sale.setProduct(product);
        sale.setSeller(seller);

        product.getSales().add(sale);

        seller.getSales().add(sale);


        return saleRepository.save(sale);
    }

    @Override
    public void delete(Sale sale) {
        saleRepository.delete(sale);
    }

    @Override
    public void deleteById(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new SaleNotFoundException(id);
        }
        saleRepository.deleteById(id);
    }

    @Override
    public Sale update(Long id, Sale sale) {

        Sale sal = saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException(id));

        Product productNew = productRepository.findById(sale.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException(sale.getProduct().getId()));

        Seller sellerNew = sellerRepository.findById(sale.getSeller().getId()).orElseThrow(() -> new SellerNotFoundException(sale.getSeller().getId()));

        if (!productNew.getSales().contains(sal)) {
            productNew.getSales().add(sal);
            sal.getProduct().getSales().remove(sal);
        }

        if (!sellerNew.getSales().contains(sal)) {
            sellerNew.getSales().add(sal);
            sal.getSeller().getSales().remove(sal);
        }

        sal.setQuantity(sale.getQuantity());
        sal.setProduct(productNew);
        sal.setSeller(sellerNew);

        return saleRepository.save(sal);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }
}
