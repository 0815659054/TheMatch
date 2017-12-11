package com.example.administrator.thematch.services;

import com.example.administrator.thematch.models.TeamSubModel;
import com.example.administrator.thematch.services.interfaces.GetTeamSubServiceInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GetTeamSubService {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("team_subscribe");

    public void getTeamSubList(final GetTeamSubServiceInterface listener) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TeamSubModel> teamSubList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    TeamSubModel teamSubModel = childSnapshot.getValue(TeamSubModel.class);
                    teamSubList.add(teamSubModel);
                }
                listener.onGetTeamSubListSuccess(teamSubList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onGetTeamSubListFail(databaseError);
            }
        });
    }

}
