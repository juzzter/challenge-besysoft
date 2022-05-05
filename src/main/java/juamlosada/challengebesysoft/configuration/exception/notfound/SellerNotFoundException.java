package juamlosada.challengebesysoft.configuration.exception.notfound;

import juamlosada.challengebesysoft.configuration.exception.NotFoundException;

public class SellerNotFoundException extends NotFoundException {

    public SellerNotFoundException(Long id) {
        super("Seller not found with id " + id);
    }

}
