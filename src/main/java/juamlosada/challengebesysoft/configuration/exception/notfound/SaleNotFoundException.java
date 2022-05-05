package juamlosada.challengebesysoft.configuration.exception.notfound;

import juamlosada.challengebesysoft.configuration.exception.NotFoundException;

public class SaleNotFoundException extends NotFoundException {

    public SaleNotFoundException(Long id) {
        super("Sale not found with id " + id);
    }

}
