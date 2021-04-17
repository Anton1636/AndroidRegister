package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;
import com.example.testapp.constants.Urls;
import com.example.testapp.dto.LoginResultDto;
import com.example.testapp.dto.RegisterDTO;
import com.example.testapp.network.AccountService;
import com.example.testapp.network.ImageRequester;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ImageRequester imageRequester;
    private NetworkImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String url = Urls.BASE+"/images/1.jpg";
        //String url = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg?resize=750px:*";
        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myImage);
        imageRequester.setImageFromUrl(myImage, url);
    }

    public void onClickRegister(View view) {
        final TextInputLayout displayNameLayout = findViewById(R.id.inputLayoutDisplayName);
        final TextInputEditText displayName = findViewById(R.id.textFieldDisplayName);

        final TextInputLayout emailLayout = findViewById(R.id.inputLayoutEmail);
        final TextInputEditText email = findViewById(R.id.textFieldEmail);

        final TextInputLayout emailPassword = findViewById(R.id.inputLayoutPassword);
        final TextInputEditText password = findViewById(R.id.textFieldPassword);
        //Log.d("clickLogin", email.getText().toString());
        //emailLayout.setError("У нас проблеми");
        RegisterDTO model = new RegisterDTO(
                email.getText().toString(),
                password.getText().toString(),
                displayName.getText().toString()
        );
        AccountService.getInstance()
                .getJSONApi()
                .register(model)
                .enqueue(new Callback<LoginResultDto>() {
                    @Override
                    public void onResponse(Call<LoginResultDto> call, Response<LoginResultDto> response) {
                        if(response.isSuccessful()) {
                            LoginResultDto result = response.body();
                            Log.d("Good Request", result.getToken());
                        }
                        else
                        {
                            try {
                                String json = response.errorBody().string();
                                Log.d("Bad request: ", json);
                            } catch (Exception ex) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResultDto> call, Throwable t) {

                    }
                });
    }
}