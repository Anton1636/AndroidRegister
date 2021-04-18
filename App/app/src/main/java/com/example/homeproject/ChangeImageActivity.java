package com.example.homeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.testapp.constants.Urls;
import com.example.testapp.dto.profile.ProfileResultDTO;
import com.example.testapp.dto.profile.UploadImageDto;
import com.example.testapp.network.profile.ApiWebService;
import com.example.testapp.utils.CommonUtils;
import com.oginotihiro.cropview.CropView;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeImageActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    //Вибір фото в галереї
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            CommonUtils.showLoading(this);
            Uri selectedImage = data.getData();
            CropView cropView = (CropView) findViewById(R.id.cropView);
            cropView.of(selectedImage)
                    //.withAspect(x, y)
                    .withOutputSize(100, 100)
                    .initialize(this);
            CommonUtils.hideLoading();
        }

    }

    public void RotateRightImage(View view) {
        CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()+90);
    }
    public void RotateLeftImage(View view) {
        CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()-90);
    }
    public void ChangeImage(View view) {
        CommonUtils.showLoading(this);
        CropView cropView = (CropView) findViewById(R.id.cropView);
        Bitmap croppedBitmap = cropView.getOutput();
        Matrix matrix = new Matrix();

        matrix.postRotate(cropView.getRotation());
        Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        UploadImageDto m = new UploadImageDto(encoded);

        ApiWebService.getInstance()
                .getJSONApi()
                .uploadImage(m)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        Log.d("super","Ok result good");
                        CommonUtils.hideLoading();
                        if(response.isSuccessful())
                        {
                            Intent intent = new Intent(ChangeImageActivity.this, ProfileActivity.class);
                            startActivity(intent);
//                            ProfileResultDTO result = response.body();
//                            String image = result.getImage();
//                            String url = Urls.BASE+"/images/" + image;
//
//                            email.setText(result.getEmail());
//                            imageRequester.setImageFromUrl(imageView, url);
//                            name.setText(result.getUserName());
//                            phone.setText(result.getPhone());
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
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("problem","problem API"+ t.getMessage());
                        CommonUtils.hideLoading();
                    }
                });
//        ChangeImage m = new ChangeImage();
//        m.setImage(encoded);
//        final ChangeImageActivity context =this;
//        AuthorizedService.getInstance()
//                .getJSONApi()
//                .changeImage(m)
//                .enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if(response.errorBody() == null){
//                            CommonUtils.hideLoading();
//                            Intent intent = new Intent(context, Profile.class);
//                            startActivity(intent);
//                        }
//                        CommonUtils.hideLoading();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//
//                    }
//                });
        //CropUtil.saveOutput(context, saveUri, croppedImage, quality);
    }

}
