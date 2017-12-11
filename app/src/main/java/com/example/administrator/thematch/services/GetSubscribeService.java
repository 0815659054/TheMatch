package com.example.administrator.thematch.services;

import com.example.administrator.thematch.models.TeamModel;
import com.example.administrator.thematch.services.interfaces.GetSubscribeServicelnterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetSubscribeService {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("team");

    public void getSubscribeList(final GetSubscribeServicelnterface listener) {
        ref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TeamModel> teamList = new ArrayList<>();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    TeamModel teamModel = childSnapshot.getValue(TeamModel.class);
                    teamList.add(teamModel);
                }
                listener.onGetSubscribeSuccess(teamList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onGetSubscribeListFail(databaseError);
            }
        });
    }

}
