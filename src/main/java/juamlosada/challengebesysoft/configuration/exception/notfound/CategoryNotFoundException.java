package juamlosada.challengebesysoft.configuration.exception.notfound;

import juamlosada.challengebesysoft.configuration.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(Long id) {
        super("Category not found with id " + id);
    }

}
