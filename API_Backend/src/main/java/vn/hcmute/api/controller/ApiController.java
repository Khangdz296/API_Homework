package vn.hcmute.api.controller;

import org.springframework.web.bind.annotation.*;
import vn.hcmute.api.entity.Category;
import vn.hcmute.api.entity.Product;
import vn.hcmute.api.repository.CategoryRepository;
import vn.hcmute.api.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")   // để sau này Android gọi thoải mái
public class ApiController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ApiController(CategoryRepository categoryRepository,
                         ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // 1. Hiển thị tất cả danh mục
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 2. Hiển thị tất cả sản phẩm theo từng danh mục
    @GetMapping("/categories/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable("id") Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    // 3. Hiển thị 10 sản phẩm có số lượng bán nhiều nhất
    @GetMapping("/products/top10-sold")
    public List<Product> getTop10SoldProducts() {
        return productRepository.findTop10ByOrderBySoldQuantityDesc();
    }

    // 4. Hiển thị 10 sản phẩm được tạo <= 7 ngày
    @GetMapping("/products/last7days")
    public List<Product> getLast7DaysProducts() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return productRepository
                .findTop10ByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(sevenDaysAgo);
    }
}
