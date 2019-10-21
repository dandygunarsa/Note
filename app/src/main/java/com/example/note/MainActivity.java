package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

import static com.example.note.MyApp.db;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etNama)EditText etNama;
    @BindView(R.id.etTelepon)EditText etTelepon;
    @BindView(R.id.etEmail)EditText etEmail;
    Mahasiswa mahasiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();


        setTitle("Insert Data");
    }

    @OnClick(R.id.btInsert)void buttonAddListener(){
        if (!etEmail.getText().toString().isEmpty()&&!etTelepon.getText().toString().isEmpty()&&
                !etNama.getText().toString().isEmpty()){

            mahasiswa = new Mahasiswa();
            mahasiswa.setEmail(etEmail.getText().toString());
            mahasiswa.setTelepon(etTelepon.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());

            db.userDao().insertAll(mahasiswa);
            startActivity(new Intent(MainActivity.this,ListActivity.class));
        }else {
            Toast.makeText(this, "Mohon data dilengkapi", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btList)void buttonViewListener(){
        startActivity(new Intent(MainActivity.this,ListActivity.class));
    }
}
