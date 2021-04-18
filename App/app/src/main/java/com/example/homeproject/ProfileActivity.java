package com.example.homeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.testapp.application.HomeApplication;
import com.example.testapp.constants.Urls;
import com.example.testapp.dto.profile.ProfileResultDTO;
import com.example.testapp.network.ImageRequester;
import com.example.testapp.network.profile.ApiWebService;
import com.example.testapp.security.JwtSecurityService;
import com.example.testapp.utils.CommonUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ImageRequester imageRequester;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView email = findViewById(R.id.profileEmail);
        final NetworkImageView imageView = findViewById(R.id.profileImage);
        final TextView name = findViewById(R.id.profileName);
        final TextView phone = findViewById(R.id.profilePhone);
        imageRequester = ImageRequester.getInstance();
        CommonUtils.showLoading(this);
        ApiWebService.getInstance()
                .getJSONApi()
                .profile()
                .enqueue(new Callback<ProfileResultDTO>() {
                    @Override
                    public void onResponse(Call<ProfileResultDTO> call, Response<ProfileResultDTO> response) {
//                        Log.d("super","Ok result good");
                        CommonUtils.hideLoading();
                        if(response.isSuccessful())
                        {
                            ProfileResultDTO result = response.body();
                            String image = result.getImage();
                            String url = Urls.BASE+"/images/" + image;

                            email.setText(result.getEmail());
                            imageRequester.setImageFromUrl(imageView, url);
                            name.setText(result.getUserName());
                            phone.setText(result.getPhone());
//                            Log.d("Good Request", result.getToken());
                        }
                        else
                        {
//                            try {
//                                String json = response.errorBody().string();
//                                LoginValidationDTO result = new Gson().fromJson(json, LoginValidationDTO.class);
//                                String str="";
//                                if(result.getErrors().getEmail()!=null)
//                                {
//                                    for (String item: result.getErrors().getEmail()) {
//                                        str+=item+"\n";
//                                    }
//                                }
//                                emailLayout.setError(str);
//
//                                str="";
//                                if(result.getErrors().getPassword()!=null)
//                                {
//                                    for (String item: result.getErrors().getPassword()) {
//                                        str+=item+"\n";
//                                    }
//                                }
//                                passwordLayout.setError(str);
//
//                                str="";
//                                if(result.getErrors().getInvalid()!=null)
//                                {
//                                    for (String item: result.getErrors().getInvalid()) {
//                                        str+=item+"\n";
//                                    }
//                                }
//                                textInvalid.setText(str);
//
//                                Log.d("Bad request: ", json);
//                            } catch (Exception ex) {
//
//                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileResultDTO> call, Throwable t) {
                        Log.e("problem","problem API"+ t.getMessage());
                        CommonUtils.hideLoading();
                    }
                });
    }
    public void SelectImage(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivity(intent);
//        Intent i = new Intent(
//                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
