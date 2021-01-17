package com.k.hedbul.ui.anasayfa;

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
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.k.hedbul.R;
import com.k.hedbul.SonucActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private EditText adiText;
    private Spinner cinsiyetSp , yasSp,ilgiSp, ozelSp;
    private Button bulbutton;
    List<String> cinsiyet, yas,ilgiAlani,ozel;
    //  private String[] yas={"Hepsi","7-12","13-17","18-25","25-40","40-60","60+"};
    // private String[] ilgiAlani={"Hepsi","Spor","Bilim","Sanat","Teknoloji","Sinema","Sosyal"};
    // private String[] ozel={"Yok","Doğum Günü","Sevgililer Günü","Yıl Başı","Anneler Günü","Babalar Günü","Meslek Günü"};
    private String cins,yass,ilgi,ozell;
    private ArrayAdapter<String> dataAdapterCinsiyet;
    private ArrayAdapter<String> dataAdapterYas;
    private ArrayAdapter<String> dataAdapterAlan;
    private ArrayAdapter<String> dataAdapterOzel;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_anasayfa, container, false);
        adiText=root.findViewById(R.id.adSoyad);
        cinsiyetSp=root.findViewById(R.id.cinsiyetSp);
        yasSp=root.findViewById(R.id.yasSp);
        ilgiSp=root.findViewById(R.id.ilgialaniSp);
        ozelSp=root.findViewById(R.id.ozelgunlerSp);
        bulbutton=root.findViewById(R.id.bulButon);

        MobileAds.initialize(root.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(root.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3385965964855097/3794990763");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        cinsiyet=new ArrayList<>();
        yas=new ArrayList<>();
        ilgiAlani=new ArrayList<>();
        ozel=new ArrayList<>();
        init();


        dataAdapterCinsiyet = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cinsiyet);
        dataAdapterCinsiyet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinsiyetSp.setAdapter(dataAdapterCinsiyet);
        cinsiyetSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cins=cinsiyetSp.getSelectedItem().toString();
                if(cins.equals("Kadın")){
                    ozel.remove("Babalar Günü");
                }if(cins.equals("Erkek")){
                    ozel.remove("Anneler Günü");
                }else if(!ozel.get(4).equals("Anneler Günü")){
                    ozel.add(4,"Anneler Günü");
                }else if(!ozel.get(5).equals("Babalar Günü")){
                    ozel.add(5,"Babalar Günü");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cins="Hepsi";
            }
        });

        dataAdapterYas = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yas);
        dataAdapterYas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yasSp.setAdapter(dataAdapterYas);
        yasSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                yass=yasSp.getSelectedItem().toString();
                if(yass.equals("7-12")){
                    ozel.remove("Sevgililer Günü");
                }else if(!ozel.get(2).equals("Sevgililer Günü")){
                    ozel.add(2,"Sevgililer Günü");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                yass="Hepsi";
            }
        });

        dataAdapterAlan = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ilgiAlani);
        dataAdapterAlan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilgiSp.setAdapter(dataAdapterAlan);
        ilgiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ilgi=ilgiSp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ilgi="Hepsi";
            }
        });


        dataAdapterOzel = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ozel);
        dataAdapterOzel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ozelSp.setAdapter(dataAdapterOzel);
        ozelSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ozell=ozelSp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ozell="Yok";
            }
        });


        bulbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SonucActivity.class);
                intent.putExtra("adi",adiText.getText().toString());
                intent.putExtra("cinsiyet",cins);
                intent.putExtra("yas",yass);
                intent.putExtra("ilgi",ilgi);
                intent.putExtra("ozel",ozell);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                //   Toast.makeText(getContext(), cins+yass+ilgi+ozell, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });


        return root;
    }


    private void init() {
        cinsiyet.add("Hepsi");
        cinsiyet.add("Kadın");
        cinsiyet.add("Erkek");

        yas.add("Hepsi");
        yas.add("7-12");
        yas.add("13-17");
        yas.add("18-25");
        yas.add("25-40");
        yas.add("40-60");
        yas.add("60+");

        ilgiAlani.add("Hepsi");
        ilgiAlani.add("Spor");
        ilgiAlani.add("Bilim");
        ilgiAlani.add("Sanat");
        ilgiAlani.add("Teknoloji");
        ilgiAlani.add("Sinema");
        ilgiAlani.add("Sosyal");

        ozel.add("Yok");
        ozel.add("Doğum Günü");
        ozel.add("Sevgililer Günü");
        ozel.add("Yıl Başı");
        ozel.add("Anneler Günü");
        ozel.add("Babalar Günü");
        ozel.add("Meslek Günü");
    }


}
