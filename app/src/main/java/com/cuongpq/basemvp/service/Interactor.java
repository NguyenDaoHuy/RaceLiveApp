package com.cuongpq.basemvp.service;

import com.cuongpq.basemvp.common.Constant;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.model.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public class Interactor {
    private static Interactor instance;
    private List<User> items;
    private RetrofitService service;
    private Interactor(){
        service = getServices();
    }
    private static RetrofitService getServices(){
        OkHttpClient client =
                new OkHttpClient.Builder()
                        .callTimeout(Constant.TIME_OUT_CONFIG, TimeUnit.MINUTES)
                        .connectTimeout(Constant.TIME_OUT_CONFIG, TimeUnit.MINUTES)
                        .readTimeout(Constant.TIME_OUT_CONFIG, TimeUnit.MINUTES)
                        .writeTimeout(Constant.TIME_OUT_CONFIG, TimeUnit.MINUTES)
                        .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(RetrofitService.class);
    }

    public static final Interactor getInstance(){
        if (instance == null){
            instance = new Interactor();
        }
        return instance;
    }

    public Observable<UserResponse<List<User>>> getUsers(){
        return service.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(data -> {
                    Interactor.this.items = items;
                });
    }
}
