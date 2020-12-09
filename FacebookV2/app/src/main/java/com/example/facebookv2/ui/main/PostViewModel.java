package com.example.facebookv2.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.facebookv2.data.PostsClient;
import com.example.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getPosts() {

        Observable observable = PostsClient.getINSTANCE().getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PostModel> value) {

                    postsMutableLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+ e);
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

//        compositeDisposable.add(observable);

//        compositeDisposable.clear();

    }
}