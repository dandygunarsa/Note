package com.example.note;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.note.MyApp.db;

@SuppressLint("ValidFragment")
public class UpdateDialog extends DialogFragment {
    private Bundle bundle;
    private int id,idList;
    OnclickAdapter mOnclickRecycler;

    @BindView(R.id.etEmailDialog) EditText email;
    @BindView(R.id.etTeleponDialog)EditText telepon;
    @BindView(R.id.etNamaDialog)EditText nama;

    public UpdateDialog(OnclickAdapter onclickRecycler) {
        mOnclickRecycler = onclickRecycler;

    }

    @OnClick(R.id.btUpdateDialog)void upDate(){

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(id);
        mahasiswa.setNama(nama.getText().toString());
        mahasiswa.setTelepon(telepon.getText().toString());
        mahasiswa.setEmail(email.getText().toString());
        db.userDao().update(mahasiswa);
        mOnclickRecycler.updateListener(idList,mahasiswa);
        dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.update_dialog, container, false);
        ButterKnife.bind(this,rootView);

        bundle = getArguments();
        id = bundle.getInt("id");
        idList = bundle.getInt("id_list");
        Log.e("dialog",""+id);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return rootView;
    }

}
