package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.note.MyApp.db;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.myRecyclerview)RecyclerView myRecyclerview;
    Adapter recycleAdapter;
    AppDB db;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                db.userDao().delete(recycleAdapter.getMahasiswaAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ListActivity.this,"Data terhapus",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(myRecyclerview);
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class,"mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getEmail()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getTelepon()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
        }
    }

    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter =new Adapter(this,listMahasiswas);
    }

    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }

    @OnClick(R.id.balik) void buttonBalik(){
        startActivity(new Intent(ListActivity.this,MainActivity.class));
    }
}
