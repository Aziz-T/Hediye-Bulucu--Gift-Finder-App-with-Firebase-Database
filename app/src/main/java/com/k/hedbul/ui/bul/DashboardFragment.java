package com.k.hedbul.ui.bul;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.k.hedbul.R;
import com.k.hedbul.SonucActivity;

public class DashboardFragment extends Fragment {

    private EditText adiText;
    private Spinner cinsiyetSp , yasSp,ilgiSp, ozelSp;
    private Button bulbutton;
    private String[] cinsiyet={"Hepsi","Kadın","Erkek"};
    private String[] yas={"Hepsi","7-12","13-17","18-25","25-40","40-60","60+"};
    private String[] ilgiAlani={"Hepsi","Spor","Bilim","Sanat","Teknoloji","Sinema","Sosyal"};
    private String[] ozel={"Yok","Doğum Günü","Sevgililer Günü","Yıl Başı","Anneler Günü","Babalar Günü","Meslek Günü"};
    private ArrayAdapter<String> dataAdapterCinsiyet;
    private ArrayAdapter<String> dataAdapterYas;
    private ArrayAdapter<String> dataAdapterAlan;
    private ArrayAdapter<String> dataAdapterOzel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bul, container, false);
        adiText=root.findViewById(R.id.adSoyad);
        cinsiyetSp=root.findViewById(R.id.cinsiyetSp);
        yasSp=root.findViewById(R.id.yasSp);
        ilgiSp=root.findViewById(R.id.ilgialaniSp);
        ozelSp=root.findViewById(R.id.ozelgunlerSp);
        bulbutton=root.findViewById(R.id.bulButon);
        dataAdapterCinsiyet = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cinsiyet);
        dataAdapterYas = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yas);
        dataAdapterAlan = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ilgiAlani);
        dataAdapterOzel = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ozel);

        dataAdapterCinsiyet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterYas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterAlan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterOzel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cinsiyetSp.setAdapter(dataAdapterCinsiyet);
        yasSp.setAdapter(dataAdapterYas);
        ilgiSp.setAdapter(dataAdapterAlan);
        ozelSp.setAdapter(dataAdapterOzel);

        bulbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SonucActivity.class);
                intent.putExtra("adi",adiText.getText().toString());
                intent.putExtra("cinsiyet",cinsiyetSp.getSelectedItem().toString());
                intent.putExtra("yas",yasSp.getSelectedItem().toString());
                intent.putExtra("ilgi",ilgiSp.getSelectedItem().toString());
                intent.putExtra("ozel",ozelSp.getSelectedItem().toString());
                startActivity(intent);

            }
        });


        return root;
    }


}
