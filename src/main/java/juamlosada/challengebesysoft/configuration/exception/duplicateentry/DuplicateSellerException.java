package juamlosada.challengebesysoft.configuration.exception.duplicateentry;

import juamlosada.challengebesysoft.configuration.exception.DuplicateException;

public class DuplicateSellerException extends DuplicateException {

    public DuplicateSellerException(String name) {
        super("Seller " + name + " already exists");
    }

}
