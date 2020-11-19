package com.k.hedbul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SonucActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private String adi,cinsiyet,yas,ilgi,ozel;
    private TextView textSonuc;
    private List<HediyeModel> asilHediyeList;
    private List<HediyeModel> normalHediyeList;
    private String deneme="Aziz,bugün,evde.";
    private int position=0, i;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        adi=getIntent().getStringExtra("adi");
        cinsiyet=getIntent().getStringExtra("cinsiyet");
        yas=getIntent().getStringExtra("yas");
        ilgi=getIntent().getStringExtra("ilgi");
        ozel=getIntent().getStringExtra("ozel");
        recyclerView=findViewById(R.id.sonucRec);

        textSonuc=findViewById(R.id.textSonuc);
        asilHediyeList=new ArrayList<>();
        normalHediyeList=new ArrayList<>();

        final HediyeAdapter hediyeAdapter = new HediyeAdapter(asilHediyeList);
        recyclerView.setAdapter(hediyeAdapter);


        myRef.child("Hediyeler").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    normalHediyeList.add(dataSnapshot.getValue(HediyeModel.class));
                }
                hediyeAdapter.notifyDataSetChanged();
                for(i=0 ; i<normalHediyeList.size(); i++){
                    if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                            normalHediyeList.get(i).getYas().contains(yas)&&
                            normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                            normalHediyeList.get(i).getOzel().contains(ozel)){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, "başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    if(cinsiyet.equals("Hepsi")&&
                            normalHediyeList.get(i).getYas().contains(yas)&&
                            normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                            normalHediyeList.get(i).getOzel().contains(ozel)){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, "cinsiyet başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                            yas.equals("Hepsi")&&
                            normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                            normalHediyeList.get(i).getOzel().contains(ozel)){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, "yas başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                            normalHediyeList.get(i).getYas().contains(yas)&&
                            ilgi.equals("Hepsi")&&
                            normalHediyeList.get(i).getOzel().contains(ozel)){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, " ilgi başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                            normalHediyeList.get(i).getYas().contains(yas)&&
                            normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                            ozel.equals("Yok")){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, "Ozel başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    if(cinsiyet.equals("Hepsi")&&
                            yas.equals("Hepsi")&&
                            ilgi.equals("Hepsi")&&
                            ozel.equals("Yok")){
                        asilHediyeList.add(normalHediyeList.get(i));
                        Toast.makeText(SonucActivity.this, " Hepsi başarılı" ,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SonucActivity.this, "başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SonucActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });







    }
}
