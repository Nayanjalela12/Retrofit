package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Post> postModelList;
    private PostAdapter adapter;
    private PostListViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycerlview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(this, postModelList);
        recyclerView.setAdapter(adapter);

      viewModel =   ViewModelProviders.of(this).get(PostListViewModel.class);
      viewModel.getUserViewObserver().observe(this, new Observer<List<Post>>() {
          @Override
          public void onChanged(List<Post> posts) {
              if (posts != null) {
                  postModelList = posts;
                  adapter.setPostList(posts);
              }
          }
      });
        viewModel.makeApiCall();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
//        Call<List<Post>> call = jsonPlaceholder.getPost();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                List<Post> postList = response.body();
//                PostAdapter postAdapter = new PostAdapter(MainActivity.this,postList);
//                recyclerView.setAdapter(postAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
    }
}