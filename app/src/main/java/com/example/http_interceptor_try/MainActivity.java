package com.example.http_interceptor_try;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataModel model = new DataModel(77,22,"Testing","My name is Noman");


        //serializeNulls it show the null value also...without it okhttp drop the null values.
        Gson gson = new GsonBuilder().serializeNulls().create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);






      OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

       NetworkService service = retrofit.create(NetworkService.class);


        Call<DataModel> call = service.putData(5,model);


        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                Log.d("onResponse: ","CODE:"+response.code());

                if (response.isSuccessful())
                {
                     DataModel data =  response.body();

                    Log.d( "onResponse: ","data:...."+data.toString());

                }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

                Log.d( "onFailure: ","the message:"+t.getMessage());

            }
        });





    }
}