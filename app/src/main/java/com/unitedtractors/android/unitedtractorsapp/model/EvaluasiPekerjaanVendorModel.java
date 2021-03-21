package com.unitedtractors.android.unitedtractorsapp.model;

public class EvaluasiPekerjaanVendorModel {
    private String idUser;
    private String idMapping;
    private String perisode;
    private String namaVendor;
    private String namaPekerjaan;
    private String spkNo;
    private String tglSPK;
    private Refrensi refrensi;
    private HasilPenilaian hasilPenilaian;
    private String kesimpulan;

    public EvaluasiPekerjaanVendorModel(String idUser, String idMapping, String perisode, String namaVendor, String namaPekerjaan, String spkNo, String tglSPK, Refrensi refrensi, HasilPenilaian hasilPenilaian, String kesimpulan) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.perisode = perisode;
        this.namaVendor = namaVendor;
        this.namaPekerjaan = namaPekerjaan;
        this.spkNo = spkNo;
        this.tglSPK = tglSPK;
        this.refrensi = refrensi;
        this.hasilPenilaian = hasilPenilaian;
        this.kesimpulan = kesimpulan;
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

    public String getPerisode() {
        return perisode;
    }

    public void setPerisode(String perisode) {
        this.perisode = perisode;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public String getSpkNo() {
        return spkNo;
    }

    public void setSpkNo(String spkNo) {
        this.spkNo = spkNo;
    }

    public String getTglSPK() {
        return tglSPK;
    }

    public void setTglSPK(String tglSPK) {
        this.tglSPK = tglSPK;
    }

    public Refrensi getRefrensi() {
        return refrensi;
    }

    public void setRefrensi(Refrensi refrensi) {
        this.refrensi = refrensi;
    }

    public HasilPenilaian getHasilPenilaian() {
        return hasilPenilaian;
    }

    public void setHasilPenilaian(HasilPenilaian hasilPenilaian) {
        this.hasilPenilaian = hasilPenilaian;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }

    public static class Refrensi {
        private String troupTicket;
        private String ewo;

        public Refrensi(String troupTicket, String ewo) {
            this.troupTicket = troupTicket;
            this.ewo = ewo;
        }

        public String getTroupTicket() {
            return troupTicket;
        }

        public void setTroupTicket(String troupTicket) {
            this.troupTicket = troupTicket;
        }

        public String getEwo() {
            return ewo;
        }

        public void setEwo(String ewo) {
            this.ewo = ewo;
        }
    }

    public static class HasilPenilaian {
        private int mutu;
        private int harga;
        private int delivery;
        private int safety;
        private int pelayanan;

        public HasilPenilaian(int mutu, int harga, int delivery, int safety, int pelayanan) {
            this.mutu = mutu;
            this.harga = harga;
            this.delivery = delivery;
            this.safety = safety;
            this.pelayanan = pelayanan;
        }

        public int getMutu() {
            return mutu;
        }

        public void setMutu(int mutu) {
            this.mutu = mutu;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public int getDelivery() {
            return delivery;
        }

        public void setDelivery(int delivery) {
            this.delivery = delivery;
        }

        public int getSafety() {
            return safety;
        }

        public void setSafety(int safety) {
            this.safety = safety;
        }

        public int getPelayanan() {
            return pelayanan;
        }

        public void setPelayanan(int pelayanan) {
            this.pelayanan = pelayanan;
        }
    }
}
