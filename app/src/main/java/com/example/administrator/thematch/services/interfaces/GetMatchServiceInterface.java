package com.example.administrator.thematch.services.interfaces;

import com.example.administrator.thematch.models.MatchModel;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public interface GetMatchServiceInterface {
    void onGetMatchListSuccess(List<MatchModel> matchList);
    void onGetMatchListFail(DatabaseError error);
}
