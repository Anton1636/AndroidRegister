package com.example.homeproject.network.account;

import com.example.testapp.dto.account.LoginDto;
import com.example.testapp.dto.account.LoginResultDto;
import com.example.testapp.dto.account.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAccount {
    @POST("/api/account/login")
    public Call<LoginResultDto> login(@Body LoginDto loginDto);
    @POST("/api/account/registration")
    public Call<LoginResultDto> register(@Body RegisterDTO registerDTO);
}

