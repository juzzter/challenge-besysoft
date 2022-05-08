package juamlosada.challengebesysoft.configuration.exception.duplicateentry;

import juamlosada.challengebesysoft.configuration.exception.DuplicateException;

public class DuplicateProductException extends DuplicateException {

    public DuplicateProductException(String name) {
        super("Product with name " + name + " already exists");
    }
}
