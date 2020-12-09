package com.example.facebookv2.data;

import com.example.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    public static final String base_url = "https://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;
    private static PostsClient INSTANCE;

    public PostsClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);

    }

    public static PostsClient getINSTANCE() {
        if (null == INSTANCE)
            INSTANCE = new PostsClient();

        return INSTANCE;

    }

    public Observable<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}