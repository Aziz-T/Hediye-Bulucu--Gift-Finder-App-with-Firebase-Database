package com.k.hedbul;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HediyeAdapter extends RecyclerView.Adapter<HediyeAdapter.ViewHolder> {
    private List<HediyeModel> hediyeModelList;


    public HediyeAdapter(List<HediyeModel> hediyeModelList) {
        this.hediyeModelList = hediyeModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hediye_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(hediyeModelList.get(position).getResimUrl(),hediyeModelList.get(position).getHediyeAdi(),position);
    }

    @Override
    public int getItemCount() {
        return hediyeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textview;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.giftImage);
            textview = itemView.findViewById(R.id.textName);
        }

        public void setData(String resimUrl, String hediyeAdi, final int position) {
            Glide.with(itemView.getContext()).load(resimUrl).into(imageView);
            textview.setText(hediyeAdi);

        }

    }
}
