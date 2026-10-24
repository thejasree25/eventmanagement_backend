
package com.jsp.eventmanagement.service;

import com.jsp.eventmanagement.entity.Category;
import java.util.List;

public interface CategoryService {

    Category create(Category category);

    List<Category> getAll();

    Category getById(Long id);

    Category update(Long id, Category category);

    void delete(Long id);
}
