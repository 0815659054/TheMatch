package com.example.administrator.thematch.services.interfaces;

import com.example.administrator.thematch.models.TeamSubModel;
import com.google.firebase.database.DatabaseError;

import java.util.List;


public interface GetTeamSubServiceInterface {
    void onAddTeamSubListSuccess(List<TeamSubModel> teamSubList);
    void onAddTeamSubListFail(DatabaseError error);
}
