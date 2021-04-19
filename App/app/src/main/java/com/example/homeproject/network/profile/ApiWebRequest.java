package com.example.homeproject.network.profile;

import com.example.homeproject.dto.profile.ProfileResultDTO;
import com.example.homeproject.dto.profile.UploadImageDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiWebRequest {
    @GET("/api/user/profile")
    public Call<ProfileResultDTO> profile();
    @GET("/api/user/users")
    public Call<List<ProfileResultDTO>> users();

    @POST("/api/user/upload-image")
    public Call<Void> uploadImage(@Body UploadImageDto uploadImageDto);
}
