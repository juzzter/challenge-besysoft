package juamlosada.challengebesysoft.services;

import juamlosada.challengebesysoft.entities.Category;

import java.util.List;

public interface CategoryService {

    public Category getById(Long id);

    public Category save(Category category);

    public void delete(Category category);

    public void deleteById(Long id);

    public Category update(Long id, Category category);

    public List<Category> findAll();

}
