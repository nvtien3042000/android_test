package com.example.dogapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsApiService;
import com.example.dogapp.viewmodel.MyAdapter;
import com.example.dogapp.viewmodel.MyViewModel;
import com.example.dogapp.viewmodel.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment  {

    private TextView tvName;
    private RecyclerView rvDogs;
    private DogsApiService apiService;
    private ImageView dogImage;

    private List<DogBreed> arrayList;
    private MyAdapter myAdapter;
    private MyViewModel model;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.dog_name);
        rvDogs = view.findViewById(R.id.rv_dogs);
        rvDogs.setLayoutManager(new GridLayoutManager(getContext(), 2));
        model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getDogBreed().observe(getViewLifecycleOwner(), new Observer<ArrayList<DogBreed>>() {
            @Override
            public void onChanged(ArrayList<DogBreed> dogBreeds) {
                arrayList = dogBreeds;
                myAdapter = new MyAdapter(arrayList, getContext());
                rvDogs.setAdapter(myAdapter);
            }
        });

        apiService = new DogsApiService();
        apiService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {

                        arrayList = dogBreeds;
                        myAdapter = new MyAdapter(arrayList, getContext());
                        rvDogs.setAdapter(myAdapter);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


}