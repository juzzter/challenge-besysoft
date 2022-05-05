package juamlosada.challengebesysoft.services.impl;

import juamlosada.challengebesysoft.configuration.exception.duplicateentry.DuplicateCategoryException;
import juamlosada.challengebesysoft.configuration.exception.notfound.CategoryNotFoundException;
import juamlosada.challengebesysoft.entities.Category;
import juamlosada.challengebesysoft.repositories.CategoryRepository;
import juamlosada.challengebesysoft.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category save(Category category) {
        if(categoryRepository.existsByName(category.getName())){
            throw new DuplicateCategoryException(category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Long id, Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            throw new DuplicateCategoryException(category.getName());
        }

        Category cat = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        cat.setName(category.getName());

        return categoryRepository.save(cat);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
