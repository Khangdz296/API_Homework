package com.example.api_homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import com.example.api_homework.model.Category;
import com.example.api_homework.model.Product;
import com.example.api_homework.api.ApiService;
import com.example.api_homework.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // thử gọi 4 API
        loadCategories();
        // loadProductsByCategory(1L);
        // loadTop10Sold();
        // loadLast7Days();
    }

    private void loadCategories() {
        ApiService api = RetrofitClient.getApiService();
        api.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> list = response.body();
                    Log.d(TAG, "Số category: " + list.size());
                    for (Category c : list) {
                        Log.d(TAG, "Category: " + c.getId() + " - " + c.getName());
                    }
                    Toast.makeText(MainActivity.this, "Load categories OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, "Lỗi gọi API: ", t);
                Toast.makeText(MainActivity.this, "API lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductsByCategory(long cateId) {
        ApiService api = RetrofitClient.getApiService();
        api.getProductsByCategory(cateId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> list = response.body();
                    Log.d(TAG, "Sản phẩm theo cate " + cateId + ": " + list.size());
                    for (Product p : list) {
                        Log.d(TAG, "Product: " + p.getId() + " - " + p.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Lỗi API product by category", t);
            }
        });
    }

    private void loadTop10Sold() {
        ApiService api = RetrofitClient.getApiService();
        api.getTop10SoldProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Top10 sold: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Lỗi API top10 sold", t);
            }
        });
    }

    private void loadLast7Days() {
        ApiService api = RetrofitClient.getApiService();
        api.getLast7DaysProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Last 7 days: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Lỗi API last7days", t);
            }
        });
    }
}