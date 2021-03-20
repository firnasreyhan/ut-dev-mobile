package com.unitedtractors.android.unitedtractorsapp.model;

import java.io.Serializable;
import java.util.List;

public class InternalWorkOrderModel {
    private String idUser;
    private String idMapping;
    private String troubTicket;
    private String tgl;
    private String tglMulai;
    private String tglSelesai;
    private String namaPemohon;
    private String div;
    private String ext;
    private String namaPenerima;
    private JenisPerbaikan jenPerbaikan;
    private String ketPerbaikan;
    private String alsnPerbaikan;
    private List<DetailKebutuhan> detKebutuhan;

    public InternalWorkOrderModel(String idUser, String idMapping, String troubTicket, String tgl, String tglMulai, String tglSelesai, String namaPemohon, String div, String ext, String namaPenerima, JenisPerbaikan jenPerbaikan, String ketPerbaikan, String alsnPerbaikan, List<DetailKebutuhan> detKebutuhan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.troubTicket = troubTicket;
        this.tgl = tgl;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.namaPemohon = namaPemohon;
        this.div = div;
        this.ext = ext;
        this.namaPenerima = namaPenerima;
        this.jenPerbaikan = jenPerbaikan;
        this.ketPerbaikan = ketPerbaikan;
        this.alsnPerbaikan = alsnPerbaikan;
        this.detKebutuhan = detKebutuhan;
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

    public String getTroubTicket() {
        return troubTicket;
    }

    public void setTroubTicket(String troubTicket) {
        this.troubTicket = troubTicket;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public JenisPerbaikan getJenPerbaikan() {
        return jenPerbaikan;
    }

    public void setJenPerbaikan(JenisPerbaikan jenPerbaikan) {
        this.jenPerbaikan = jenPerbaikan;
    }

    public String getKetPerbaikan() {
        return ketPerbaikan;
    }

    public void setKetPerbaikan(String ketPerbaikan) {
        this.ketPerbaikan = ketPerbaikan;
    }

    public String getAlsnPerbaikan() {
        return alsnPerbaikan;
    }

    public void setAlsnPerbaikan(String alsnPerbaikan) {
        this.alsnPerbaikan = alsnPerbaikan;
    }

    public List<DetailKebutuhan> getDetKebutuhan() {
        return detKebutuhan;
    }

    public void setDetKebutuhan(List<DetailKebutuhan> detKebutuhan) {
        this.detKebutuhan = detKebutuhan;
    }

    public static class JenisPerbaikan {
        private boolean pengBaru;
        private boolean pengSebagian;
        private boolean perbaikan;

        public boolean isPengBaru() {
            return pengBaru;
        }

        public void setPengBaru(boolean pengBaru) {
            this.pengBaru = pengBaru;
        }

        public boolean isPengSebagian() {
            return pengSebagian;
        }

        public void setPengSebagian(boolean pengSebagian) {
            this.pengSebagian = pengSebagian;
        }

        public boolean isPerbaikan() {
            return perbaikan;
        }

        public void setPerbaikan(boolean perbaikan) {
            this.perbaikan = perbaikan;
        }
    }

    public static class DetailKebutuhan implements Serializable {
        private String nama;
        private int quant;
        private String musNo;

        public DetailKebutuhan(String nama, int quant, String musNo) {
            this.nama = nama;
            this.quant = quant;
            this.musNo = musNo;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public int getQuant() {
            return quant;
        }

        public void setQuant(int quant) {
            this.quant = quant;
        }

        public String getMusNo() {
            return musNo;
        }

        public void setMusNo(String musNo) {
            this.musNo = musNo;
        }

        public boolean checkData()  {
            if (!getNama().isEmpty() && getQuant() != 0 && !getMusNo().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
