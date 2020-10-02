package com.example.dogapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.viewmodel.MyAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView origin;
    private TextView lifeSpan;
    private TextView temperament;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.dog_image);
        name = view.findViewById(R.id.name);
        origin = view.findViewById(R.id.origin);
        lifeSpan = view.findViewById(R.id.life_span);
        temperament = view.findViewById(R.id.temperament);

        Picasso.with(getContext()).load(getArguments().getString("url"))
                .into(image, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError() {
            }
        });

        name.setText(getArguments().getString("name"));
        origin.setText(getArguments().getString("origin"));
        lifeSpan.setText(getArguments().getString("leftSpan"));
        temperament.setText(getArguments().getString("temperament"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}