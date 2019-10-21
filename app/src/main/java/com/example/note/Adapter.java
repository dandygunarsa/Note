package com.example.note;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements OnclickAdapter{

    private Context mContext;
    private List<Mahasiswa> albumList;
    OnclickAdapter onclickRecycler = this;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNama)TextView nama;
        @BindView(R.id.tvTelepon)TextView telepon;
        @BindView(R.id.tvEmail)TextView email;

        public MyViewHolder(View v) {
            super(v);

            ButterKnife.bind(this,v);
        }
    }

    public Adapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Mahasiswa album = albumList.get(position);
        holder.nama.setText(album.getNama());
        holder.telepon.setText(album.getTelepon());
        holder.email.setText(album.getEmail());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentManager fm = ((Activity)mContext).getFragmentManager();
                UpdateDialog dialogFragment = new UpdateDialog(onclickRecycler);
                Bundle bundle = new Bundle();
                bundle.putInt("id",albumList.get(position).getId());
                bundle.putInt("id_list",position);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(fm, "");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public Mahasiswa getMahasiswaAt(int position){
        return albumList.get(position);
    }

    @Override
    public void updateListener(int id, Mahasiswa mahasiswa) {
        albumList.get(id).setEmail(mahasiswa.getEmail());
        albumList.get(id).setTelepon(mahasiswa.getTelepon());
        albumList.get(id).setNama(mahasiswa.getNama());
        notifyDataSetChanged();
    }
}
