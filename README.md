#  Ecommerce Category App - Mobile Programing

Ứng dụng Android minh họa cách kết nối API Spring Boot, xử lý JSON, hiển thị danh sách danh mục/sản phẩm và tích hợp bảo mật JWT. Đây là bài tập môn Mobile Programming áp dụng đầy đủ quy trình xây dựng App thực tế.

## Ảnh sản phẩm

<p align="center"><img width="433" height="897" alt="Image" src="https://github.com/user-attachments/assets/1397a17e-76aa-4c65-bbb8-2f0f4de78781" /></p>

##  Tính năng chính
### Backend (Spring Boot 3 + MySQL)
- CRUD danh mục (Category)
- CRUD sản phẩm (Product).
- Lọc:
  - Danh sách tất cả danh mục 
  - Sản phẩm theo từng danh mục 
  - Top 10 sản phẩm bán chạy nhất 
  - 10 sản phẩm được tạo trong vòng 7 ngày gần đây 
- REST API theo chuẩn JSON
- Bảo mật API bằng Spring Security + JWT
- Tích hợp Swagger UI để test API

<img width="1580" height="578" alt="Image" src="https://github.com/user-attachments/assets/5b55b30f-5cf7-49b9-9a0b-e4234a60d40b" />
  
### Android App (Java)
- Gọi API bằng Retrofit 2
- Xử lý JSON bằng GSON
- Hiển thị danh mục và sản phẩm bằng RecyclerView
- Dùng Glide để tải và cache ảnh
- Màn hình:
  - Danh mục
  - Sản phẩm theo danh mục
  - Top 10 bán chạy
  - Sản phẩm mới trong 7 ngày
  - Đăng nhập nhận JWT token → Lưu SharedPreferences → Gọi API có Authorization

<p align=center><img width="426" height="905" alt="Image" src="https://github.com/user-attachments/assets/f6eb88c3-9668-4dda-9694-2a110cb17d6d" />
<img width="428" height="908" alt="Image" src="https://github.com/user-attachments/assets/0faccb91-4556-47cb-bca5-c497841bc325" /></p>

##  Công nghệ & Thư viện sử dụng

### Backend API
| Công nghệ | Mô tả |
| :--- | :--- |
| Spring Boot 3 | API RESTful |
| Spring Data JPA | ORM với MySQL |
| Spring Security 6 | Bảo mật API |
| JWT (jjwt 0.11.5) | Token đăng nhập |
| MySQL | Database |
| Swagger (springdoc-openapi) | Giao diện test API |

### Android App
| Thư viện | Mục đích |
| :--- | :--- |
| Retrofit 2 | Gọi API |
| GSON | Convert JSON ↔ Java object |
| Glide | Load & cache ảnh |
| RecyclerView + CardView | Hiển thị danh sách |
| SharedPreferences | Lưu token |

##  Thông tin API
- **Base URL Android:** `http://10.0.2.2:8080/`
### Endpoints chính
| Method | Endpoint | Chức năng |
| :--- | :--- | :--- |
| POST | `/api/auth/login` | Đăng nhập nhận JWT |
| GET | `/api/categories` | Lấy tất cả danh mục |
| GET | `/api/categories/{id}/products` | Sản phẩm theo danh mục |
| GET | `/api/products/top10-sold` | Top 10 bán chạy |
| GET | `/api/products/last7days` | 10 sản phẩm mới nhất |

##  Cấu trúc Project
```text
com.example.api_homework
├── model
│   ├── Category.java
│   ├── Product.java
│   └── AuthResponse.java
│
├── api
│   ├── RetrofitClient.java   # Singleton + Interceptor gắn JWT
│   ├── APIService.java       # Interface định nghĩa API
│   └── AuthInterceptor.java  # Tự động gắn Authorization
│
├── adapter
│   ├── CategoryAdapter.java
│   └── ProductAdapter.java
│
├── utils
│   └── TokenManager.java     # Lưu token vào SharedPreferences
│
├── ui
│   ├── LoginActivity.java
│   ├── CategoryActivity.java
│   └── ProductActivity.java
│
└── MainActivity.java
```

## Tác giả
**Tên: Dương Đình Ngọc Khang** 

**MSSV: 23162036**
