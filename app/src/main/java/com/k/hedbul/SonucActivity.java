package com.k.hedbul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
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
    private ProgressBar progressBar;

    private int i;
    private RecyclerView recyclerView;
    private AdView mAdView;
   /* private String[] cinsiyetDizi={"Kadın","Erkek"};
    private String[] yasDizi={"7-12","13-17","18-25","25-40","40-60","60+"};
    private String[] ilgiDizi={"Spor","Bilim","Sanat","Teknoloji","Sinema","Sosyal"};
    private String[] ozelDizi={"Doğum Günü","Sevgililer Günü","Yıl Başı","Anneler Günü","Babalar Günü","Meslek Günü"};*/

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
        progressBar=findViewById(R.id.progressBar);
        textSonuc=findViewById(R.id.textSonuc);

        asilHediyeList=new ArrayList<>();
        normalHediyeList=new ArrayList<>();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        final HediyeAdapter hediyeAdapter = new HediyeAdapter(asilHediyeList,this);
        recyclerView.setAdapter(hediyeAdapter);
        if(adi.equals("")||adi==null){
            adi="Adsız";
        }
       // Toast.makeText(this, cinsiyet+yas+ilgi+ozel, Toast.LENGTH_SHORT).show();


        myRef.child("Hediyeler").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    normalHediyeList.add(dataSnapshot.getValue(HediyeModel.class));
                }
                hediyeAdapter.notifyDataSetChanged();
                sorgula();
                if(asilHediyeList.size()>0) progressBar.setVisibility(View.GONE);
                textSonuc.setText(adi+" kişisi için bulunan hediyeler: "+ asilHediyeList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SonucActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });







    }

    private void sorgula() {
        for(i=0 ; i<normalHediyeList.size(); i++){
            if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            }
            //cinsiyet
            if(cinsiyet.equals("Hepsi")&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            } if(cinsiyet.equals("Hepsi")&&
                    yas.equals("Hepsi")&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            } if(cinsiyet.equals("Hepsi")&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    ilgi.equals("Hepsi")&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            } if(cinsiyet.equals("Hepsi")&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }
            //cinsiyet

            //yas
            if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    yas.equals("Hepsi")&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    yas.equals("Hepsi")&&
                    ilgi.equals("Hepsi")&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    yas.equals("Hepsi")&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }
            //yas


            if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    ilgi.equals("Hepsi")&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    ilgi.equals("Hepsi")&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }


            if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    yas.equals("Hepsi")&&
                    ilgi.equals("Hepsi")&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(cinsiyet.equals("Hepsi")&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    ilgi.equals("Hepsi")&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(cinsiyet.equals("Hepsi")&&
                    yas.equals("Hepsi")&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }if(cinsiyet.equals("Hepsi")&&
                    yas.equals("Hepsi")&&
                    ilgi.equals("Hepsi")&&
                    normalHediyeList.get(i).getOzel().contains(ozel)){
                asilHediyeList.add(normalHediyeList.get(i));

            }


            if(normalHediyeList.get(i).getCinsiyet().contains(cinsiyet)&&
                    normalHediyeList.get(i).getYas().contains(yas)&&
                    normalHediyeList.get(i).getIlgi().contains(ilgi)&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }
            if(cinsiyet.equals("Hepsi")&&
                    yas.equals("Hepsi")&&
                    ilgi.equals("Hepsi")&&
                    ozel.equals("Yok")){
                asilHediyeList.add(normalHediyeList.get(i));

            }
            else{

            }
        }
    }
}
