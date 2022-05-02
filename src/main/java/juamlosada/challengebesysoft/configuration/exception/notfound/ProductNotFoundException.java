package juamlosada.challengebesysoft.configuration.exception.notfound;

import juamlosada.challengebesysoft.configuration.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException(Long id) {
        super("Product not found with id " + id);
    }

}
