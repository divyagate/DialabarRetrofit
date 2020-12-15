package com.example.dialabarretrofit;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.example.dialabarretrofit.Api_interfaces.JsonPlaceHolderApi;
import com.example.dialabarretrofit.Models.register;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    register reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textview);
      reg = new register("manjul", "manjul@gmail.com", "12345");
        getPosts();
    }

    public void getPosts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.75:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi JsonHolder = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<register>> call = JsonHolder.getPosts();

        call.enqueue(new Callback<List<register>>() {
            @Override
            public void onResponse(Call<List<register>> call, Response<List<register>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code :" + response.code());

                    return;

                }

                List<register> posts = response.body();

                for (register post : posts) {
                    String content = " ";
                    content += "First Name: " + post.getName() + "\n";
                    content += "Password: " + post.getPassword() + "\n";
                    content += "Address: " + post.getEmail() + "\n\n\n";
                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<register>> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void createPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.75:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi JsonHolder = retrofit.create(JsonPlaceHolderApi.class);
        Call<register> call = JsonHolder.createPost(reg);

        call.enqueue(new Callback<register>() {
            @Override
            public void onResponse(Call<register> call, Response<register> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code :" + response.code());
                    Toast.makeText(MainActivity.this,"msg"+response.body().getId(),Toast.LENGTH_LONG).show();
                    return;
                }

                register post = response.body();

                String content = "";
                content += "Response Code: " + response.code() + "\n";
                content += "First Name: " + post.getName() + "\n";
                content += "Password: " + post.getPassword() + "\n";
                content += "Address: " + post.getEmail() + "\n";

                textViewResult.append(content);


            }

            @Override
            public void onFailure(Call<register> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
}