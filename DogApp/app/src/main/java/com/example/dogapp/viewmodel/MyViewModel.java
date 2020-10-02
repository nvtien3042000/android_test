package com.example.dogapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogapp.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<DogBreed>> dogBreed;

    public LiveData<ArrayList<DogBreed>> getDogBreed() {
        if (dogBreed == null){
            dogBreed = new MutableLiveData<>();
        }
        return dogBreed;
    }

    public void setDogBreed(ArrayList<DogBreed> dogBreedArrayList) {
        dogBreed.setValue(dogBreedArrayList);
    }
}
