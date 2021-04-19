package com.example.homeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.homeproject.constants.Urls;
import com.example.homeproject.dto.profile.ProfileResultDTO;
import com.example.homeproject.myrecycler.CustomAdapter;
import com.example.homeproject.myrecycler.Model;
import com.example.homeproject.network.profile.ApiWebService;
import com.example.homeproject.utils.CommonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false));

//        List<Model> list = new ArrayList<>();
//        Model model = new Model();
//        model.setName("Іван");
//        model.setVersion("0.1");
//        list.add(model);
//        Model model1 = new Model();
//        model1.setName("Іванка");
//        model1.setVersion("2.1");
//        list.add(model1);
        ApiWebService.getInstance()
                .getJSONApi()
                .users()
                .enqueue(new Callback<List<ProfileResultDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProfileResultDTO>> call, Response<List<ProfileResultDTO>> response) {
                        if(response.isSuccessful())
                        {
                            List<ProfileResultDTO> result = response.body();
                            customAdapter = new CustomAdapter(result,  RecyclerActivity.this);
                            recyclerView.setAdapter(customAdapter);
//                          Log.d("Good Request", result.getToken());
                        }
                        else
                        {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProfileResultDTO>> call, Throwable t) {

                    }
                });

    }
}
