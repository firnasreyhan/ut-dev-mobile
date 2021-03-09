package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForGensetModel;
import com.unitedtractors.android.unitedtractorsapp.model.ExternalWorkOrderModel;
import com.unitedtractors.android.unitedtractorsapp.model.Pertanyaan2Model;
import com.unitedtractors.android.unitedtractorsapp.repository.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TroublesInformationViewModel extends AndroidViewModel {
    private Repository repository;

    public TroublesInformationViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<BaseResponse> postICGS(ChecklistForGensetModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTgl());
            paramObject.put("lokasi", model.getLokasi());
            paramObject.put("engine", model.getEngine());
            paramObject.put("engineModel", model.getEngineModel());
            paramObject.put("serial", model.getSerial());
            paramObject.put("geno", model.getGeno());
            paramObject.put("serial2", model.getSerial2());
            paramObject.put("hourMeter", model.getHourMeter());

            paramObject.put("egnOil", jsonArray(model.getUnitEngine(), 0));
            paramObject.put("egnOilPress", jsonArray(model.getUnitEngine(), 1));
            paramObject.put("radiator", jsonArray(model.getUnitEngine(), 2));
            paramObject.put("radiatorHose", jsonArray(model.getUnitEngine(), 3));
            paramObject.put("fanBelt", jsonArray(model.getUnitEngine(), 4));
            paramObject.put("battery", jsonArray(model.getUnitEngine(), 5));
            paramObject.put("electrolyt", jsonArray(model.getUnitEngine(), 6));
            paramObject.put("starterMotor", jsonArray(model.getUnitEngine(), 7));
            paramObject.put("oilPressIndicator", jsonArray(model.getUnitEngine(), 8));

            paramObject.put("ampereMeter", jsonArray(model.getControlSystem(), 0));
            paramObject.put("freqMeter", jsonArray(model.getControlSystem(), 1));
            paramObject.put("voltMeter", jsonArray(model.getControlSystem(), 2));
            paramObject.put("relay", jsonArray(model.getControlSystem(), 3));
            paramObject.put("mcb", jsonArray(model.getControlSystem(), 4));
            paramObject.put("terminal", jsonArray(model.getControlSystem(), 5));
            paramObject.put("emergency", jsonArray(model.getControlSystem(), 6));

            paramObject.put("ruangan", jsonArray(model.getK5(), 0));
            paramObject.put("runningTest", jsonArray(model.getK5(), 1));

            paramObject.put("probIdentification", model.getProbIdentification());
            paramObject.put("rootCause", model.getRootCause());
            paramObject.put("correctAct", model.getCorrectAct());
            paramObject.put("preventAct", model.getPreventAct());
            paramObject.put("deadLine", model.getDeadLine());
            paramObject.put("status", model.getStatus());
            paramObject.put("condGenset", model.getCondGenset());

            return repository.postICGS(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return repository.postICGS(null);
    }

    public JSONArray jsonArray(List<Pertanyaan2Model> list, int i) throws JSONException {
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("status", list.get(i).getStatus());
        object.put("catatan", list.get(i).getCatatan());
        array.put(object);

        return array;
    }
}
