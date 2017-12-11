package com.example.administrator.thematch.services;

import com.example.administrator.thematch.models.TeamSubModel;
import com.example.administrator.thematch.services.interfaces.AddTeamSubServiceInterface;
import com.example.administrator.thematch.services.interfaces.GetTeamSubServiceInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.List;

public class AddTeamSubService {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("team_subscribe");
    private GetTeamSubService getTeamSubService = new GetTeamSubService();
    private AddTeamSubServiceInterface listener;

    public void addSubscription(final Long teamId, final String token) {
        getTeamSubService.getTeamSubList(new GetTeamSubServiceInterface() {
            @Override
            public void onGetTeamSubListSuccess(List<TeamSubModel> teamSubList) {
                for (TeamSubModel teamSub : teamSubList) {
                    if (teamSub.team_id.equals(teamId)) {
                        teamSub.token.add(token);
                        break;
                    }
                }
                saveSubscriptionWithFirebase(teamSubList);
            }

            @Override
            public void onGetTeamSubListFail(DatabaseError error) {
                if (listener != null) {
                    listener.onAddTeamSubscriptionFail();
                }
            }
        });
    }

    private void saveSubscriptionWithFirebase(final List<TeamSubModel> teamSubList) {
        ref.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                mutableData.setValue(teamSubList);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (listener != null && databaseError != null) {
                    listener.onAddTeamSubscriptionFail();
                } else if (listener != null) {
                    listener.onAddTeamSubscriptionSuccess();
                }
            }
        });
    }

    public void addListener(AddTeamSubServiceInterface listener) {
        this.listener = listener;
    }

}
