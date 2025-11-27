package vn.hcmute.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.api.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // tất cả sản phẩm theo category id
    List<Product> findByCategory_Id(Long categoryId);

    // top 10 sản phẩm bán nhiều nhất
    List<Product> findTop10ByOrderBySoldQuantityDesc();

    // 10 sản phẩm tạo trong 7 ngày gần nhất
    List<Product> findTop10ByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDateTime fromDate);
}
