package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, Add_Dialog.ExampleDialog {
    private ArrayList<Person> contacts;
    private RecyclerView rvContacts;
    private MyAdapter myAdapter;
    private SearchView searchView;
    private FloatingActionButton bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contacts = new ArrayList<Person>();
        Person p1 = new Person("Nguyễn Văn A", "0982829999", "a.@gmail.com", "Người thân");
        Person p2 = new Person("Trần Văn B", "0982829999", "b.gmail.com", "Người thân");
        Person p3 = new Person("Lê Văn C", "0982829999", "c.mail.com", "Bạn");
        Person p4 = new Person("Nguyễn Văn D", "0982829999", "d.gmail.com", "Bạn");
        Person p5 = new Person("Lý Văn E", "0982829999", "E.@gmail.com", "Người thân");
        Person p6 = new Person("Cao Anh F", "0982829999", "F.gmail.com", "Người thân");
        Person p7 = new Person("Hồ Văn H", "0982829999", "H.mail.com", "Bạn");
        Person p8 = new Person("Nguyễn Hữu I", "0982829999", "I.gmail.com", "Bạn");
        Person p9 = new Person("Nguyễn Hữu G", "0982829999", "I.gmail.com", "Bạn");
        contacts.add(p1);
        contacts.add(p2);
        contacts.add(p3);
        contacts.add(p4);
        contacts.add(p5);
        contacts.add(p6);
        contacts.add(p7);
        contacts.add(p8);
        contacts.add(p9);

        rvContacts = findViewById(R.id.rv_contacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(contacts, this);
        rvContacts.setAdapter(myAdapter);

        searchView = findViewById(R.id.sv_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });

        bAdd = findViewById(R.id.b_add);
    }


    @Override
    public void onItemClick(Person person) {
        Intent intent = new Intent(MainActivity.this, DetailContact.class);
        intent.putExtra("name", person.getName());
        intent.putExtra("phoneNumber", person.getPhoneNumber());
        intent.putExtra("sms", person.getSms());
        intent.putExtra("inf", person.getInf());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(Person person) {
        contacts.remove(person);
        myAdapter.notifyDataSetChanged();

//        Toast.makeText(this,person.getName(), LENGTH_SHORT).show();
    }

    public void onClickAddButton(View v){
        openDialog();
    }

    public void openDialog(){
        Add_Dialog addDialog =  new Add_Dialog();
        addDialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void applyText(String name, String phone, String email, String inf) {
        Person person = new Person(name,phone,email,inf);
        contacts.add(person);
        myAdapter.notifyDataSetChanged();
    }



}