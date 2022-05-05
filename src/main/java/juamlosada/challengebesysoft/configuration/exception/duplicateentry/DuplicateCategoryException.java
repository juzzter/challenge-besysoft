package juamlosada.challengebesysoft.configuration.exception.duplicateentry;

import juamlosada.challengebesysoft.configuration.exception.DuplicateException;

public class DuplicateCategoryException extends DuplicateException {

    public DuplicateCategoryException(String name) {
        super("Category with name " + name + " already exists");
    }

}
