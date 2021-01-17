package com.k.hedbul;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    private Context context;


    public HediyeAdapter(List<HediyeModel> hediyeModelList,Context context) {
        this.hediyeModelList = hediyeModelList;
        this.context=context;

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

    public void filterList(ArrayList<HediyeModel> filteredList){
        hediyeModelList=filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {
        private ImageView imageView;
        private TextView textview;
        private ImageView popUpButon;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.giftImage);
            textview = itemView.findViewById(R.id.textName);
            popUpButon= itemView.findViewById(R.id.popUpButon);
        }

        public void setData(String resimUrl, String hediyeAdi, final int position) {
            Glide.with(itemView.getContext()).load(resimUrl).into(imageView);
            textview.setText(hediyeAdi);
            popUpButon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(view,position);
                }
            });

        }

        private void showPopupMenu(View view, final int position) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.hediye_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.googleIt:
                            searchNet(hediyeModelList.get(position).getHediyeAdi());
                            return true;

                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        }



        private void searchNet(String aranan){
            try{
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,aranan);
                context.startActivity(intent);
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
                searchNetCombat(aranan);
            }
        }
        private void searchNetCombat(String aranan){
            try{
                Uri uri = Uri.parse("http://www.google.com/#q="+aranan);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.putExtra(SearchManager.QUERY,aranan);
                context.startActivity(intent);
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
                Toast.makeText(context, "Hata!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }
    }
}
