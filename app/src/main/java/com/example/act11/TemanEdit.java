package com.example.act11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.act11.MainActivity;
import com.example.act11.R;
import com.example.act11.TemanEdit;
import com.example.act11.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TemanEdit extends AppCompatActivity {
    private TextView tv_Key;
    private EditText ed_Nama, ed_Telpon;
    private Button btnEdit;
    private DatabaseReference database;
    String kode, nama, telpon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        ed_Nama = findViewById(R.id.edBarang);
        ed_Telpon = findViewById(R.id.edKode);
        tv_Key = findViewById(R.id.tv_key);
        btnEdit = findViewById(R.id.btEdit);

        database = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("kunci1");
        nama = bundle.getString("kunci2");
        telpon = bundle.getString("kunci3");



        tv_Key.setText("Key : "+kode);
        ed_Nama.setText(nama);
        ed_Telpon.setText(telpon);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeman(new Teman(ed_Nama.getText().toString(), ed_Telpon.getText().toString()));
            }
        });
    }

    public void editTeman(Teman nama){
        database.child("Teman")
                .child(kode)
                .setValue(nama)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TemanEdit.this, "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
    }
}