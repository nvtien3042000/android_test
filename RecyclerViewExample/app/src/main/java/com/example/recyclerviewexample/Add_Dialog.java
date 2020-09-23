package com.example.recyclerviewexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Add_Dialog extends AppCompatDialogFragment {

    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etInf;
    private ExampleDialog exampleDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_contact_dialog, null);

        builder.setView(view)
                .setTitle("Add Contact")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = etName.getText().toString();
                        String phone = etPhone.getText().toString();
                        String email = etEmail.getText().toString();
                        String inf = etInf.getText().toString();
                        exampleDialog.applyText(name,phone,email,inf);
                    }
                });
        etName = view.findViewById(R.id.et_name);
        etPhone = view.findViewById(R.id.et_phone);
        etEmail = view.findViewById(R.id.et_email);
        etInf = view.findViewById(R.id.et_inf);
        return  builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            exampleDialog = (ExampleDialog) context;
        } catch (Exception e){
            e.printStackTrace();
            throw new ClassCastException(context.toString());
        }
    }

    public interface ExampleDialog{
        void applyText(String name, String phone, String email, String inf);
    }
}
