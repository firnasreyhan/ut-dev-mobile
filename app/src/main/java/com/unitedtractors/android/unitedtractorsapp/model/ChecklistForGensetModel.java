package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ChecklistForGensetModel {
    private String idUser;
    private String idMapping;
    private String tgl;
    private String lokasi;
    private String engine;
    private String engineModel;
    private String serial;
    private String geno;
    private String serial2;
    private String hourMeter;
    private List<Pertanyaan2Model> unitEngine;
    private List<Pertanyaan2Model> controlSystem;
    private List<Pertanyaan2Model> k5;
    private String probIdentification;
    private String rootCause;
    private String correctAct;
    private String preventAct;
    private String deadLine;
    private String status;
    private String condGenset;

    public ChecklistForGensetModel(String idUser, String idMapping, String tgl, String lokasi, String engine, String engineModel, String serial, String geno, String serial2, String hourMeter, List<Pertanyaan2Model> unitEngine, List<Pertanyaan2Model> controlSystem, List<Pertanyaan2Model> k5, String probIdentification, String rootCause, String correctAct, String preventAct, String deadLine, String status, String condGenset) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tgl = tgl;
        this.lokasi = lokasi;
        this.engine = engine;
        this.engineModel = engineModel;
        this.serial = serial;
        this.geno = geno;
        this.serial2 = serial2;
        this.hourMeter = hourMeter;
        this.unitEngine = unitEngine;
        this.controlSystem = controlSystem;
        this.k5 = k5;
        this.probIdentification = probIdentification;
        this.rootCause = rootCause;
        this.correctAct = correctAct;
        this.preventAct = preventAct;
        this.deadLine = deadLine;
        this.status = status;
        this.condGenset = condGenset;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdMapping() {
        return idMapping;
    }

    public void setIdMapping(String idMapping) {
        this.idMapping = idMapping;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getGeno() {
        return geno;
    }

    public void setGeno(String geno) {
        this.geno = geno;
    }

    public String getSerial2() {
        return serial2;
    }

    public void setSerial2(String serial2) {
        this.serial2 = serial2;
    }

    public String getHourMeter() {
        return hourMeter;
    }

    public void setHourMeter(String hourMeter) {
        this.hourMeter = hourMeter;
    }

    public List<Pertanyaan2Model> getUnitEngine() {
        return unitEngine;
    }

    public void setUnitEngine(List<Pertanyaan2Model> unitEngine) {
        this.unitEngine = unitEngine;
    }

    public List<Pertanyaan2Model> getControlSystem() {
        return controlSystem;
    }

    public void setControlSystem(List<Pertanyaan2Model> controlSystem) {
        this.controlSystem = controlSystem;
    }

    public List<Pertanyaan2Model> getK5() {
        return k5;
    }

    public void setK5(List<Pertanyaan2Model> k5) {
        this.k5 = k5;
    }

    public String getProbIdentification() {
        return probIdentification;
    }

    public void setProbIdentification(String probIdentification) {
        this.probIdentification = probIdentification;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getCorrectAct() {
        return correctAct;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public String getPreventAct() {
        return preventAct;
    }

    public void setPreventAct(String preventAct) {
        this.preventAct = preventAct;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondGenset() {
        return condGenset;
    }

    public void setCondGenset(String condGenset) {
        this.condGenset = condGenset;
    }
}
