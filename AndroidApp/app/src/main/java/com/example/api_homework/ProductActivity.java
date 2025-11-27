package com.example.api_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_homework.adapter.ProductAdapter;
import com.example.api_homework.model.Product;
import com.example.api_homework.api.ApiService;
import com.example.api_homework.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private TextView tvTitle;
    private ProductAdapter adapter;
    private List<Product> productList = new ArrayList<>();

    private static final String TAG = "ProductList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        rvProducts = findViewById(R.id.rvProducts);
        tvTitle = findViewById(R.id.tvTitle);

        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(productList);
        rvProducts.setAdapter(adapter);

        String mode = getIntent().getStringExtra("mode");
        if (mode == null) mode = "by_category";

        String title = getIntent().getStringExtra("title");
        if (title != null) {
            tvTitle.setText(title);
        }

        switch (mode) {
            case "by_category":
                long cateId = getIntent().getLongExtra("categoryId", -1);
                if (cateId != -1) loadProductsByCategory(cateId);
                break;
            case "top10_sold":
                loadTop10Sold();
                break;
            case "last7days":
                loadLast7Days();
                break;
        }
    }

    private void loadProductsByCategory(long cateId) {
        ApiService api = RetrofitClient.getApiService();
        api.getProductsByCategory(cateId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductActivity.this, "Lỗi load sản phẩm", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API error", t);
            }
        });
    }

    private void loadTop10Sold() {
        ApiService api = RetrofitClient.getApiService();
        api.getTop10SoldProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Lỗi API top10 sold", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadLast7Days() {
        ApiService api = RetrofitClient.getApiService();
        api.getLast7DaysProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Lỗi API last7days", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
