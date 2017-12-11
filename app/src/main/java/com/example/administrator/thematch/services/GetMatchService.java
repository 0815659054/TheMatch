package com.example.administrator.thematch.services;

import com.example.administrator.thematch.models.MatchModel;
import com.example.administrator.thematch.services.interfaces.GetMatchServiceInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetMatchService {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("match");


    public void getMatchList(final GetMatchServiceInterface listener) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<MatchModel> matchList = new ArrayList<>();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    MatchModel matchModel = childSnapshot.getValue(MatchModel.class);
                    matchList.add(matchModel);
                }
                listener.onGetMatchListSuccess(matchList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onGetMatchListFail(databaseError);
            }
        });
    }

}
