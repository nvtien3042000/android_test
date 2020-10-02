package com.example.dogapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.ListFragmentDirections;
import com.example.dogapp.MainActivity;
import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<DogBreed> arrayList;
    private Context context;

    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;



    public MyAdapter(List<DogBreed> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(arrayList.get(position).isShowMenu()){//=================
//            return SHOW_MENU;
//        }else{
//            return HIDE_MENU;
//        }
//    }




    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dog, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tvDogName.setText(arrayList.get(position).getName());
        String url = arrayList.get(position).getUrl();
        Picasso.with(context).load(url).into(((MyViewHolder) holder).dogImage, new Callback() {
            @Override
            public void onSuccess() {
                ((MyViewHolder) holder).progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError() {
            }
        });
        holder.tvDogTe.setText(arrayList.get(position).getTemperament());
        holder.setDogBreed(arrayList.get(position));


        //version 2
//        DogBreed dogBreed = arrayList.get(position);
//        if(holder instanceof MyViewHolder){
//            //... same as above
//        }
//
//        if(holder instanceof MenuViewHolder){ //====================
//            //Menu Actions
//        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private DogBreed dogBreed;
        public View view;
        public ImageView dogImage;
        public TextView tvDogName;
        public  TextView tvDogTe;
        private ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);

            this.view = view;
            dogImage = this.view.findViewById(R.id.dog_image);
            progressBar = this.view.findViewById(R.id.pb_loading);
            tvDogName = this.view.findViewById(R.id.dog_name);
            tvDogTe = this.view.findViewById(R.id.te_dog);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavDirections action = ListFragmentDirections.actionListFragmentToDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", dogBreed.getUrl());
                    bundle.putString("name",dogBreed.getName());
                    bundle.putString("origin",dogBreed.getOrigin());
                    bundle.putString("leftSpan",dogBreed.getLifeSpan());
                    bundle.putString("temperament",dogBreed.getTemperament());
                    Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);
                }
            });

            this.view = view;
        }

        public void setDogBreed(DogBreed dogBreed){
            this.dogBreed = dogBreed;
        }

    }

//    class MenuViewHolder extends RecyclerView.ViewHolder{
//        public MenuViewHolder(View view){
//            super(view);
//        }
//    }
}

