package com.example.api_homework.api;

import com.example.api_homework.model.AuthRequest;
import com.example.api_homework.model.AuthResponse;
import com.example.api_homework.model.Category;
import com.example.api_homework.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // 1. Tất cả danh mục
    @GET("api/categories")
    Call<List<Category>> getAllCategories();

    // 2. Tất cả sản phẩm theo từng danh mục
    @GET("api/categories/{id}/products")
    Call<List<Product>> getProductsByCategory(@Path("id") long categoryId);

    // 3. Top 10 sản phẩm bán nhiều nhất
    @GET("api/products/top10-sold")
    Call<List<Product>> getTop10SoldProducts();

    // 4. 10 sản phẩm tạo trong 7 ngày
    @GET("api/products/last7days")
    Call<List<Product>> getLast7DaysProducts();

    @POST("api/auth/login")
    Call<AuthResponse> login(@Body AuthRequest request);

//    @POST("api/auth/register")
//    Call<String> register(@Body RegisterRequest request);
}
