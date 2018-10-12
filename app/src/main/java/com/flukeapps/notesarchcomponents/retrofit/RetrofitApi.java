package com.flukeapps.notesarchcomponents.retrofit;

import com.flukeapps.notesarchcomponents.model.Scene;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("get_notes.php")
    Call<List<Scene>> getWebNotes();
}
