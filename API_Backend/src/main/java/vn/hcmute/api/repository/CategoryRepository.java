package vn.hcmute.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
