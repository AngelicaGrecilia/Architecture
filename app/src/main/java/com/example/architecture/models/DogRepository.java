package com.example.architecture.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.architecture.services.ApiClient;
import com.example.architecture.services.DogServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {
    private DogServices dogServices;

    public MutableLiveData<DogRandomResponse> getDogLiveData() {
        return dogLiveData;
    }

    private MutableLiveData<DogRandomResponse> dogLiveData = new MutableLiveData<>();

    public DogRepository() {
        dogServices = ApiClient.getRetrofitInstance().create(DogServices.class);
    }

    public LiveData<DogRandomResponse> getRandomDogData() {
        dogServices.fetchRandomDog().enqueue(new Callback<DogRandomResponse>() {
            @Override
            public void onResponse(Call<DogRandomResponse> call, Response<DogRandomResponse> response) {
                dogLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DogRandomResponse> call, Throwable t) {
                dogLiveData.setValue(null);
            }
        });
        return dogLiveData;
    }

}
