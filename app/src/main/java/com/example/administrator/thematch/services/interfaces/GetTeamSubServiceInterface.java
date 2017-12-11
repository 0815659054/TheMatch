package com.example.administrator.thematch.services.interfaces;

import com.example.administrator.thematch.models.TeamSubModel;
import com.google.firebase.database.DatabaseError;

import java.util.List;


public interface GetTeamSubServiceInterface {
    void onGetTeamSubListSuccess(List<TeamSubModel> teamSubList);
    void onGetTeamSubListFail(DatabaseError error);
}
