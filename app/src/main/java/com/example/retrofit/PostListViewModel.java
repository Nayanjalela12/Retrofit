package com.example.retrofit;

import static com.example.retrofit.RetrofitInstance.retrofit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListViewModel extends ViewModel {

    private MutableLiveData<List<Post>> postView;

    public  PostListViewModel(){

        postView = new MutableLiveData<>();
    }

    public MutableLiveData<List<Post>>getUserViewObserver(){

        return postView;
    }

    public void makeApiCall(){
        JSONPlaceholder jsonPlaceholder = RetrofitInstance.getRetroClient().create(JSONPlaceholder.class);
        Call<List<Post>> call = jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//
                postView.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                 postView.postValue(null);
            }
        });
    }
}
