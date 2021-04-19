package com.example.homeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;
import com.example.homeproject.application.HomeApplication;
import com.example.homeproject.constants.Urls;
import com.example.homeproject.dto.account.LoginResultDto;
import com.example.homeproject.dto.account.RegisterDTO;
import com.example.homeproject.dto.account.RegisterValidationDTO;
import com.example.homeproject.network.account.AccountService;
import com.example.homeproject.network.ImageRequester;
import com.example.homeproject.security.JwtSecurityService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

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

        final TextInputLayout passwordLayout = findViewById(R.id.inputLayoutPassword);
        final TextInputEditText password = findViewById(R.id.textFieldPassword);

        final TextInputLayout phoneLayout = findViewById(R.id.inputLayoutPhone);
        final TextInputEditText phone = findViewById(R.id.textFieldPhone);
        //Log.d("clickLogin", email.getText().toString());
        //emailLayout.setError("У нас проблеми");
        RegisterDTO model = new RegisterDTO(
                email.getText().toString(),
                password.getText().toString(),
                displayName.getText().toString(),
                phone.getText().toString()
        );
        AccountService.getInstance()
                .getJSONApi()
                .register(model)
                .enqueue(new Callback<LoginResultDto>() {
                    @Override
                    public void onResponse(Call<LoginResultDto> call, Response<LoginResultDto> response) {

                        if(response.isSuccessful()) {
                            displayNameLayout.setError("");
                            emailLayout.setError("");
                            passwordLayout.setError("");
                            phoneLayout.setError("");
                            LoginResultDto result = response.body();
                            JwtSecurityService jwtService = (JwtSecurityService) HomeApplication.getInstance();
                            jwtService.saveJwtToken(result.getToken());
                            Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            try {
                                String json = response.errorBody().string();
                                RegisterValidationDTO result = new Gson().fromJson(json, RegisterValidationDTO.class);
                                String str="";
                                if(result.getErrors().getDisplayName()!=null)
                                {
                                    for (String item: result.getErrors().getDisplayName()) {
                                        str+=item+"\n";
                                    }
                                }
                                displayNameLayout.setError(str);

                                str="";
                                if(result.getErrors().getEmail()!=null)
                                {
                                    for (String item: result.getErrors().getEmail()) {
                                        str+=item+"\n";
                                    }
                                }
                                emailLayout.setError(str);

                                str="";
                                if(result.getErrors().getPhone()!=null)
                                {
                                    for (String item: result.getErrors().getPhone()) {
                                        str+=item+"\n";
                                    }
                                }
                                phoneLayout.setError(str);

                                str="";
                                if(result.getErrors().getPassword()!=null)
                                {
                                    for (String item: result.getErrors().getPassword()) {
                                        str+=item+"\n";
                                    }
                                }
                                passwordLayout.setError(str);

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

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
