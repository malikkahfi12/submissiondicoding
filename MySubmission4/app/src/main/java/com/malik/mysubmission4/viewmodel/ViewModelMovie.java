package com.malik.mysubmission4.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.malik.mysubmission4.R;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.helper.HttpRequest;
import com.malik.mysubmission4.interfaces.ResponseObject;
import com.malik.mysubmission4.pojo.Movie.ListMovie;
import com.malik.mysubmission4.pojo.Movie.ResultsItem;

import org.json.JSONObject;

import java.util.ArrayList;

public class ViewModelMovie extends ViewModel {
    private MutableLiveData<ArrayList<ResultsItem>> listResultMovie = new MutableLiveData<>();
    private HttpRequest httpRequest = new HttpRequest();

    private String languageApi;

    public void setMovie(Context  context){
        final ArrayList<ResultsItem> resultMovieItems = new ArrayList<>();
        languageApi = context.getString(R.string.language);
        httpRequest.getRequest(context, UrlAPI.MOVIE, UrlAPI.API_KEY, languageApi , new ResponseObject() {
            @Override
            public void jsonResponse(JSONObject object) {
                // TODO: 2/1/20 Parsing JSON POJO 
                ListMovie listMovie = new Gson().fromJson(object.toString(), ListMovie.class);
                for (int i = 0; i < listMovie.getResults().size(); i++){
                    ResultsItem resultsItem = new ResultsItem();
                    resultsItem.setTitle(listMovie.getResults().get(i).getTitle());
                    resultsItem.setOverview(listMovie.getResults().get(i).getOverview());
                    resultsItem.setPosterPath(listMovie.getResults().get(i).getPosterPath());
                    resultsItem.setId(listMovie.getResults().get(i).getId());
                    resultsItem.setReleaseDate(listMovie.getResults().get(i).getReleaseDate());
                    resultsItem.setVoteAverage(listMovie.getResults().get(i).getVoteAverage());
                    resultMovieItems.add(resultsItem);
                }
                listResultMovie.postValue(resultMovieItems);
            }
        });
    }

    public LiveData<ArrayList<ResultsItem>> getResultMovie(){
        return listResultMovie;
    }
}
