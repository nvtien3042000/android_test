package com.example.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Person> mContacts;
    private OnItemClickListener listener;
    private ArrayList<Person> mContactsAll;

    public  MyAdapter(ArrayList<Person> mContacts, OnItemClickListener listener){
        this.mContacts = mContacts;
        this.listener = listener;
        this.mContactsAll = new ArrayList<Person>(mContacts);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(mContacts.get(position).getName());
        holder.bindData(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }



    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Person> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(mContactsAll);
            } else {
                for (Person contact:mContactsAll) {
                    if(contact.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    filteredList.add(contact);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mContacts.clear();
            mContacts.addAll((Collection<? extends Person>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private Person person ;
        public View view;
        public TextView tvName;
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(person);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(person);
                    return false;
                }
            });
            this.view = view;
            tvName = this.view.findViewById(R.id.tv_name);
        }

        public void bindData(Person person){
            this.person = person;
        }

    }
}
