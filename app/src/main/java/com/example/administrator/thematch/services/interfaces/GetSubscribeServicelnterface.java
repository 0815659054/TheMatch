package com.example.administrator.thematch.services.interfaces;

import com.example.administrator.thematch.models.TeamModel;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public interface GetSubscribeServicelnterface {
    void onGetSubscribeSuccess(List<TeamModel> teamList);
    void onGetSubscribeListFail(DatabaseError error);
}
