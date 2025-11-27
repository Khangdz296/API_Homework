package vn.hcmute.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    // số lượng tồn kho (nếu cần)
    private int quantity;

    // số lượng đã bán – dùng cho top 10 bán nhiều nhất
    @Column(name = "sold_quantity")
    private int soldQuantity;

    // ngày tạo sản phẩm – để lọc 7 ngày gần nhất
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Quan hệ nhiều-1 với Category
    @ManyToOne
    @JoinColumn(name = "category_id") // FK trong bảng products
    private Category category;

    public Product() {}

    // ===== GETTER / SETTER =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
