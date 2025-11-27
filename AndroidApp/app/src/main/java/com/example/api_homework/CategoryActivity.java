package com.example.api_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.api_homework.adapter.CategoryAdapter;
import com.example.api_homework.model.Category;
import com.example.api_homework.api.ApiService;
import com.example.api_homework.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private List<Category> categoryList = new ArrayList<>();

    private static final String TAG = "CategoryList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CategoryAdapter(categoryList, category -> {
            // Khi click category -> sang màn ProductList
            Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
            intent.putExtra("mode", "by_category");
            intent.putExtra("categoryId", category.getId());
            intent.putExtra("title", "Sản phẩm của " + category.getName());
            startActivity(intent);
        });
        rvCategories.setAdapter(adapter);

        Button btnTop10 = findViewById(R.id.btnTop10);
        btnTop10.setOnClickListener(v -> {
            Intent i = new Intent(CategoryActivity.this, ProductActivity.class);
            i.putExtra("mode", "top10_sold");
            i.putExtra("title", "Top 10 sản phẩm bán chạy");
            startActivity(i);
        });

        // NÚT 10 SP MỚI 7 NGÀY
        Button btnLast7 = findViewById(R.id.btnLast7);
        btnLast7.setOnClickListener(v -> {
            Intent i = new Intent(CategoryActivity.this, ProductActivity.class);
            i.putExtra("mode", "last7days");
            i.putExtra("title", "Sản phẩm mới trong 7 ngày");
            startActivity(i);
        });

        loadCategories();
    }

    private void loadCategories() {
        ApiService api = RetrofitClient.getApiService();
        api.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CategoryActivity.this, "Lỗi load category", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API error", t);
            }
        });
    }
}
