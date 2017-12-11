package com.example.administrator.thematch.services;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RemoveTeamSubService {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("team_subscribe");

    public void removeSubscription(final Long teamId,final String token){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot tokenSnapShot : childSnapshot.child("token").getChildren()){
                        String oldToken = tokenSnapShot.getValue(String.class);
                        if (oldToken.equals(token)) {
                            tokenSnapShot.getRef().removeValue();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
