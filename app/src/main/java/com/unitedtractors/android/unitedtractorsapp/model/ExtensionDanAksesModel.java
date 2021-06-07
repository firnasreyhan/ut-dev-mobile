package com.unitedtractors.android.unitedtractorsapp.model;

import java.util.List;

public class ExtensionDanAksesModel {
    private String idUser;
    private String idMapping;
    private List<DetExtension> detExtension;

    public ExtensionDanAksesModel(String idUser, String idMapping, List<DetExtension> detExtension) {
        this.idUser = idUser;
        this.idMapping = idMapping;
        this.detExtension = detExtension;
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

    public List<DetExtension> getDetExtension() {
        return detExtension;
    }

    public void setDetExtension(List<DetExtension> detExtension) {
        this.detExtension = detExtension;
    }

    public static class DetExtension{
        private String nama;
        private String nrp;
        private String noExtension;
        private String div;
        private String aksesExisting;
        private String aksesBaru;
        private ContactTo contactTo;

        public DetExtension(String nama, String nrp, String noExtension, String div, String aksesExisting, String aksesBaru, ContactTo contactTo) {
            this.nama = nama;
            this.nrp = nrp;
            this.noExtension = noExtension;
            this.div = div;
            this.aksesExisting = aksesExisting;
            this.aksesBaru = aksesBaru;
            this.contactTo = contactTo;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNrp() {
            return nrp;
        }

        public void setNrp(String nrp) {
            this.nrp = nrp;
        }

        public String getNoExtension() {
            return noExtension;
        }

        public void setNoExtension(String noExtension) {
            this.noExtension = noExtension;
        }

        public String getDiv() {
            return div;
        }

        public void setDiv(String div) {
            this.div = div;
        }

        public String getAksesExisting() {
            return aksesExisting;
        }

        public void setAksesExisting(String aksesExisting) {
            this.aksesExisting = aksesExisting;
        }

        public String getAksesBaru() {
            return aksesBaru;
        }

        public void setAksesBaru(String aksesBaru) {
            this.aksesBaru = aksesBaru;
        }

        public ContactTo getContactTo() {
            return contactTo;
        }

        public void setContactTo(ContactTo contactTo) {
            this.contactTo = contactTo;
        }

        public boolean checkData()  {
            if (!getNoExtension().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public static class ContactTo {
            private boolean principle;
            private boolean uthi;
            private boolean cabang;
            private boolean partner;
            private boolean customer;
            private boolean vendor;
            private boolean subCont;
            private boolean hotel;

            public ContactTo(boolean principle, boolean uthi, boolean cabang, boolean partner, boolean customer, boolean vendor, boolean subCont, boolean hotel) {
                this.principle = principle;
                this.uthi = uthi;
                this.cabang = cabang;
                this.partner = partner;
                this.customer = customer;
                this.vendor = vendor;
                this.subCont = subCont;
                this.hotel = hotel;
            }

            public boolean isPrinciple() {
                return principle;
            }

            public void setPrinciple(boolean principle) {
                this.principle = principle;
            }

            public boolean isUthi() {
                return uthi;
            }

            public void setUthi(boolean uthi) {
                this.uthi = uthi;
            }

            public boolean isCabang() {
                return cabang;
            }

            public void setCabang(boolean cabang) {
                this.cabang = cabang;
            }

            public boolean isPartner() {
                return partner;
            }

            public void setPartner(boolean partner) {
                this.partner = partner;
            }

            public boolean isCustomer() {
                return customer;
            }

            public void setCustomer(boolean customer) {
                this.customer = customer;
            }

            public boolean isVendor() {
                return vendor;
            }

            public void setVendor(boolean vendor) {
                this.vendor = vendor;
            }

            public boolean isSubCont() {
                return subCont;
            }

            public void setSubCont(boolean subCont) {
                this.subCont = subCont;
            }

            public boolean isHotel() {
                return hotel;
            }

            public void setHotel(boolean hotel) {
                this.hotel = hotel;
            }
        }
    }
}
