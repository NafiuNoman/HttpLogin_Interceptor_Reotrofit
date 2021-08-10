package com.example.http_interceptor_try;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkService {

//    https://jsonplaceholder.typicode.com/posts/5
    @PUT("posts/{id}")
    Call<DataModel> putData(@Path("id") int id, @Body DataModel model);

}
