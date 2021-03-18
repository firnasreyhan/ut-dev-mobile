package com.unitedtractors.android.unitedtractorsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.unitedtractors.android.unitedtractorsapp.api.response.BaseResponse;
import com.unitedtractors.android.unitedtractorsapp.model.ChecklistForHydrantModel;
import com.unitedtractors.android.unitedtractorsapp.repository.OnlineRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PanelHydrantViewModel extends AndroidViewModel {
    private OnlineRepository onlineRepository;

    public PanelHydrantViewModel(@NonNull Application application) {
        super(application);
        onlineRepository = new OnlineRepository();
    }

    public MutableLiveData<BaseResponse> postICH(ChecklistForHydrantModel model) {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("idUser", model.getIdUser());
            paramObject.put("idMapping", model.getIdMapping());
            paramObject.put("tgl", model.getTanggal());
            paramObject.put("lokasi", model.getLokasi());

            paramObject.put("sistemPipa", array(model.getSystemPemipaan()));
            paramObject.put("jockey", array(model.getJockeyPump()));
            paramObject.put("electric", array(model.getElectricPump()));
            paramObject.put("diesel", array(model.getDieselHydrantPump()));
            paramObject.put("panel", array(model.getPanelHydrant()));

//            paramObject.put("kebocoranPipa", object(model.getSystemPemipaan().get(0)));
//            paramObject.put("flangePipa", object(model.getSystemPemipaan().get(1)));
//            paramObject.put("posisiValve", object(model.getSystemPemipaan().get(2)));
//
//            paramObject.put("gateJockey", object(model.getJockeyPump().get(0)));
//            paramObject.put("kipasJockey", object(model.getJockeyPump().get(1)));
//            paramObject.put("powerJockey", object(model.getJockeyPump().get(2)));
//            paramObject.put("motorJockey", object(model.getJockeyPump().get(3)));
//
//            paramObject.put("gateElectric", object(model.getElectricPump().get(0)));
//            paramObject.put("kipasElectric", object(model.getElectricPump().get(1)));
//            paramObject.put("powerElectric", object(model.getElectricPump().get(2)));
//            paramObject.put("motorElectric", object(model.getElectricPump().get(3)));
//
//            paramObject.put("gateDiesel", object(model.getDieselHydrantPump().get(0)));
//            paramObject.put("radiatorDiesel", object(model.getDieselHydrantPump().get(1)));
//            paramObject.put("oliDiesel", object(model.getDieselHydrantPump().get(2)));
//            paramObject.put("fanbeltDiesel", object(model.getDieselHydrantPump().get(3)));
//            paramObject.put("batteryDiesel", object(model.getDieselHydrantPump().get(4)));
//            paramObject.put("rpmDiesel", object(model.getDieselHydrantPump().get(5)));
//            paramObject.put("runningDiesel", object(model.getDieselHydrantPump().get(6)));
//
//            paramObject.put("switchPanel", object(model.getPanelHydrant().get(0)));
//            paramObject.put("indikatorPanel", object(model.getPanelHydrant().get(1)));
//            paramObject.put("voltmeterPanel", object(model.getPanelHydrant().get(2)));
//            paramObject.put("amperePanel", object(model.getPanelHydrant().get(3)));
//            paramObject.put("konektorPanel", object(model.getPanelHydrant().get(4)));

            paramObject.put("catatan", model.getCatatan());
            return onlineRepository.postICH(paramObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return onlineRepository.postICH(null);
    }

//    private JSONObject object(ChecklistForHydrantModel.DetailChecklistHydrant model) {
//        JSONObject object = new JSONObject();
//        try {
//            object.put("status", String.valueOf(model.getStatus()));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return object;
//    }

    private JSONArray array(List<ChecklistForHydrantModel.DetailChecklistHydrant> list) {
        JSONArray array = new JSONArray();
        for (ChecklistForHydrantModel.DetailChecklistHydrant hydrant : list) {
            JSONObject object = new JSONObject();
            try {
                object.put("status", String.valueOf(hydrant.getStatus()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(object);
        }

        return array;
    }
}
