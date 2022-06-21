package com.cuongpq.basemvp.service;

import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.model.response.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/api/users")
    Observable<UserResponse<List<User>>> getUsers();
}
