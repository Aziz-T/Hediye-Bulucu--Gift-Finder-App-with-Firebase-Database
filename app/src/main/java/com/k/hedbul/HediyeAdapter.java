package com.k.hedbul;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

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
        holder.setData(hediyeModelList.get(position).getResimUrl(),hediyeModelList.get(position).getHediyeAdi());
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
            imageView=itemView.findViewById(R.id.giftImage);
            textview=itemView.findViewById(R.id.textName);
        }

        public void setData(String resimUrl, String hediyeAdi) {
            Glide.with(itemView.getContext()).load(resimUrl).into(imageView);
            this.textview.setText(hediyeAdi);
        }
    }
}
