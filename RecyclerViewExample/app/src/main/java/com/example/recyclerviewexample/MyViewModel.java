package com.example.recyclerviewexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Person>> mContacts;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Person> arrayList;



    public LiveData<ArrayList<Person>> getArray(){
        if(mContacts == null){
            mContacts = new MutableLiveData<>();
            arrayList = new ArrayList<>();
            mContacts.setValue(arrayList);
        }
        return mContacts;
    }

    public void setArray(Person person){
        arrayList.add(person);
        mContacts.setValue(arrayList);
    }
}
