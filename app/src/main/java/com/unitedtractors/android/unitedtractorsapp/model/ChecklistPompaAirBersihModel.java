package com.unitedtractors.android.unitedtractorsapp.model;

public class ChecklistPompaAirBersihModel {
    private String idUser;
    private String idMapping;
    private String tgl;
    private String lokasi;
    private DetailMinggu minggu1;
    private DetailMinggu minggu2;
    private DetailMinggu minggu3;
    private DetailMinggu minggu4;

    public ChecklistPompaAirBersihModel(String idUser, String idMapping, String tgl, String lokasi, DetailMinggu minggu1, DetailMinggu minggu2, DetailMinggu minggu3, DetailMinggu minggu4) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.tgl = tgl;
        this.lokasi = lokasi;
        this.minggu1 = minggu1;
        this.minggu2 = minggu2;
        this.minggu3 = minggu3;
        this.minggu4 = minggu4;
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

    public DetailMinggu getMinggu1() {
        return minggu1;
    }

    public void setMinggu1(DetailMinggu minggu1) {
        this.minggu1 = minggu1;
    }

    public DetailMinggu getMinggu2() {
        return minggu2;
    }

    public void setMinggu2(DetailMinggu minggu2) {
        this.minggu2 = minggu2;
    }

    public DetailMinggu getMinggu3() {
        return minggu3;
    }

    public void setMinggu3(DetailMinggu minggu3) {
        this.minggu3 = minggu3;
    }

    public DetailMinggu getMinggu4() {
        return minggu4;
    }

    public void setMinggu4(DetailMinggu minggu4) {
        this.minggu4 = minggu4;
    }

    public static class DetailMinggu {
        private String tgl;
        private DetailChecklist kondAir;
        private DetailChecklist airPancingan;
        private DetailChecklist indikator;
        private DetailChecklist tekUdara;
        private DetailChecklist flowMeter;
        private DetailChecklist supplyAir;
        private DetailChecklist manSupply;
        private DetailChecklist fungsiPanel;

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public DetailChecklist getKondAir() {
            return kondAir;
        }

        public void setKondAir(DetailChecklist kondAir) {
            this.kondAir = kondAir;
        }

        public DetailChecklist getAirPancingan() {
            return airPancingan;
        }

        public void setAirPancingan(DetailChecklist airPancingan) {
            this.airPancingan = airPancingan;
        }

        public DetailChecklist getIndikator() {
            return indikator;
        }

        public void setIndikator(DetailChecklist indikator) {
            this.indikator = indikator;
        }

        public DetailChecklist getTekUdara() {
            return tekUdara;
        }

        public void setTekUdara(DetailChecklist tekUdara) {
            this.tekUdara = tekUdara;
        }

        public DetailChecklist getFlowMeter() {
            return flowMeter;
        }

        public void setFlowMeter(DetailChecklist flowMeter) {
            this.flowMeter = flowMeter;
        }

        public DetailChecklist getSupplyAir() {
            return supplyAir;
        }

        public void setSupplyAir(DetailChecklist supplyAir) {
            this.supplyAir = supplyAir;
        }

        public DetailChecklist getManSupply() {
            return manSupply;
        }

        public void setManSupply(DetailChecklist manSupply) {
            this.manSupply = manSupply;
        }

        public DetailChecklist getFungsiPanel() {
            return fungsiPanel;
        }

        public void setFungsiPanel(DetailChecklist fungsiPanel) {
            this.fungsiPanel = fungsiPanel;
        }

        public static class DetailChecklist {
            private boolean status;
            private String keterangan;

            public DetailChecklist(boolean status, String keterangan) {
                this.status = status;
                this.keterangan = keterangan;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }

            public String getKeterangan() {
                return keterangan;
            }

            public void setKeterangan(String keterangan) {
                this.keterangan = keterangan;
            }
        }
    }
}
