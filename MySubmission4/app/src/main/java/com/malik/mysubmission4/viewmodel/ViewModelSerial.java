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
import com.malik.mysubmission4.pojo.Serial.ListSerial;
import com.malik.mysubmission4.pojo.Serial.ResultsItem;

import org.json.JSONObject;

import java.util.ArrayList;

public class ViewModelSerial extends ViewModel {
    private MutableLiveData<ArrayList<ResultsItem>> mutableListSerial = new MutableLiveData<>();
    private HttpRequest httpRequest = new HttpRequest();
    private String languageApi;
    public void setSerial(Context context){
        languageApi = context.getString(R.string.language);
        final ArrayList<ResultsItem> arrayResultsItems = new ArrayList<>();
        httpRequest.getRequest(context, UrlAPI.SERIAL, UrlAPI.API_KEY, languageApi, new ResponseObject() {
            @Override
            public void jsonResponse(JSONObject object) {
                ListSerial listSerial = new Gson().fromJson(object.toString(), ListSerial.class);
                for (int i = 0; i < listSerial.getResults().size(); i++){
                    ResultsItem resultsItem = new ResultsItem();
                    resultsItem.setName(listSerial.getResults().get(i).getName());
                    resultsItem.setOverview(listSerial.getResults().get(i).getOverview());
                    resultsItem.setPosterPath(listSerial.getResults().get(i).getPosterPath());
                    resultsItem.setId(listSerial.getResults().get(i).getId());
                    resultsItem.setVoteAverage(listSerial.getResults().get(i).getVoteAverage());
                    resultsItem.setFirstAirDate(listSerial.getResults().get(i).getFirstAirDate());
                    arrayResultsItems.add(resultsItem);
                }
                mutableListSerial.postValue(arrayResultsItems);
            }
        });
    }

    public LiveData<ArrayList<ResultsItem>> getResultSerial(){
        return mutableListSerial;
    }
}
