package com.jrektor.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Products> productsList;
    ApiInterface apiInterface;
    Adapter.ViewHolder.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.pb_main);
        recyclerView = findViewById(R.id.rv_main);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.ViewHolder.RecyclerViewClickListener() {
            @Override
            public void onRowClick(CardView rowContainer, int adapterPosition) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("id", productsList.get(adapterPosition).getId());
                intent.putExtra("name", productsList.get(adapterPosition).getName());
                intent.putExtra("brand",productsList.get(adapterPosition).getBrand());
                intent.putExtra("type",productsList.get(adapterPosition).getType());
                intent.putExtra("price",productsList.get(adapterPosition).getPrice());
                intent.putExtra("date",productsList.get(adapterPosition).getDate());
                intent.putExtra("image",productsList.get(adapterPosition).getImage());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.btn_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
    }

    public void getProduct(){
        Call<List<Products>> call = apiInterface.getProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                progressBar.setVisibility(View.GONE);
                productsList = response.body();
                Log.i(MainActivity.class.getSimpleName(),response.body().toString());
                adapter = new Adapter(productsList, MainActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProduct();
    }
}