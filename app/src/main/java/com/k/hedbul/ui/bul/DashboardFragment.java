package com.k.hedbul.ui.bul;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.k.hedbul.HediyeAdapter;
import com.k.hedbul.HediyeModel;
import com.k.hedbul.R;
import com.k.hedbul.SonucActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private RecyclerView recyclerView;
   // private EditText aramaEdit;
    private TextView oneri;

    private List<HediyeModel> asilHediyeList;
    private List<HediyeModel> normalHediyeList;
    private int position=0,i;
    private ProgressBar progressBar;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_bul, container, false);
      //  aramaEdit=root.findViewById(R.id.aramaEdit);
        recyclerView=root.findViewById(R.id.rastRec);
        oneri=root.findViewById(R.id.textOneri);
        progressBar=root.findViewById(R.id.progressBar);
        MobileAds.initialize(root.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(root.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-8277541514565651/1490596327");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

        asilHediyeList=new ArrayList<>();
        normalHediyeList=new ArrayList<>();

        final HediyeAdapter hediyeAdapter = new HediyeAdapter(asilHediyeList,getContext());
        recyclerView.setAdapter(hediyeAdapter);
        oneri.setText("Rastgele Öneriler");
        myRef.child("Hediyeler").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    normalHediyeList.add(dataSnapshot.getValue(HediyeModel.class));
                }
                hediyeAdapter.notifyDataSetChanged();
               /* aramaEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        oneri.setText("Arama Sonuçları");
                        hediyeAdapter.filterList(filter(editable.toString()));
                        if(editable.toString().equals("")){
                            oneri.setText("Rastgele Öneriler");
                        }
                    }
                });*/

                //  Random random = new Random();
                for(i=0; i<25 ; i++){
                    //position=random.nextInt(normalHediyeList.size());
                    asilHediyeList.add(normalHediyeList.get(i));
                }
                if(asilHediyeList.size()>0) progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    /*private ArrayList<HediyeModel> filter(String text) {
        ArrayList<HediyeModel> filteredList = new ArrayList<>();
        for(HediyeModel hediyeModel:normalHediyeList){
            if(hediyeModel.getHediyeAdi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(hediyeModel);
            }
        }
        return filteredList;
    }*/
}
