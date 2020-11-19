package com.k.hedbul.ui.anasayfa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.k.hedbul.HediyeAdapter;
import com.k.hedbul.HediyeModel;
import com.k.hedbul.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private RecyclerView recyclerView;

    private List<HediyeModel> asilHediyeList;
    private List<HediyeModel> normalHediyeList;
    private int position=0,i;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_anasayfa, container, false);

        recyclerView=root.findViewById(R.id.rastRec);

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
                Random random = new Random();
                for(i=0; i<30 ; i++){
                    position=random.nextInt(normalHediyeList.size());
                    asilHediyeList.add(normalHediyeList.get(position));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}
