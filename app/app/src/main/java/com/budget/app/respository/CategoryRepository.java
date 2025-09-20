package com.budget.app.respository;
import org.springframework.data.repository.CrudRepository;
import com.budget.app.entity.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>{

    
}
