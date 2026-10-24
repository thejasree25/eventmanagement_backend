
package com.jsp.eventmanagement.serviceimpl;

import com.jsp.eventmanagement.entity.Category;
import com.jsp.eventmanagement.repository.CategoryRepository;
import com.jsp.eventmanagement.service.CategoryService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        repository.findByNameIgnoreCase(category.getName())
                .ifPresent(c -> {
                    throw new IllegalArgumentException(
                            "Category already exists");
                });

        return repository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category not found"));
    }

    @Override
    public Category update(Long id, Category category) {
        Category existing = getById(id);
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
