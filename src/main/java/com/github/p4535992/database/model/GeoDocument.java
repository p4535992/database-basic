
package com.github.p4535992.database.model;


import com.github.p4535992.database.hibernate.interceptor.SelfDirtyCheckingEntity;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * GeoDocument.java.
 * La classe che costruisce lt'oggeto JAVA geoDocument su cui andiamo a
 * inserire i risultati del'elaborazione di GATE e inserirli successivamente
 * come record della tabella geodocument del database geolocationdb
 */
@Entity
@Table(name = "geodocument")
public class GeoDocument extends SelfDirtyCheckingEntity{
    //private static final long serialVersionUID = 11L;
    @Id
    @GeneratedValue
    @Column(name = "doc_id")
    private Integer doc_id;
    @Column(name = "url")
    private URL url;
    @Column(name = "regione")
    private String regione;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "city")
    private String city;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "iva")
    private String iva;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "edificio")
    private String edificio;
    @Column(name = "nazione")
    private String nazione;
    @Column(name = "latitude")
    private Double lat;
    @Column(name = "longitude")
    private Double lng;
               
    /////////////////////////
    @Column(name = "description")
    private String description;
    @Column(name = "indirizzoNoCAP")
    private String indirizzoNoCAP;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "fax")
    private String fax;
    /////////////////////////////////
    @Column(name = "indirizzoHasNumber")
    private String indirizzoHasNumber;

    public GeoDocument (){}

    /*
     * GeoDocument da inserire nella tabrella InfoDocument
     * @param url
     * @param regione
     * @param provincia
     * @param city
     * @param indirizzo
     * @param iva
     * @param email
     * @param telefono
     * @param fax
     * @param edificio
     * @param lat
     * @param lng
     * @param nazione
     * @param description
     * @param postalCode
     * @param indirizzoNoCAP
     */
    public GeoDocument(URL url, String regione, String provincia, String city,
                       String indirizzo, String iva, String email, String telefono, String fax,
                       String edificio, Double lat, Double lng, String nazione, String description,
                       String postalCode, String indirizzoNoCAP, String indirizzoHasNumber) {
		this.url = url;
		this.regione = regione;
        this.provincia = provincia;
		this.city = city;		
		this.indirizzo = indirizzo;
        this.iva = iva;
		this.email = email;
		this.telefono = telefono;
        this.fax = fax;
		this.edificio = edificio;
		this.lat = lat;
		this.lng = lng;
        this.nazione = nazione;
        this.description = description;
        this.indirizzoNoCAP = indirizzoNoCAP;
        this.postalCode = postalCode;
        this.indirizzoHasNumber = indirizzoHasNumber;
	}


    public Integer getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Integer doc_id) {
        this.doc_id = doc_id;
        markDirtyProperty();
    }

    public URL getUrl() {
        return url;
    }


    public void setUrl(URL url) {
        if(url.toString().contains("://")) {
            this.url = url;
        }else{
            try {
                this.url = new URL("http://"+url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        markDirtyProperty();
    }

//    @PreUpdate
//    @PrePersist
//    public void setUrl(String url) {
//        if(url.toString().contains("://")) {
//            try {
//                this.url = new URL(url);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }else{
//            try {
//                this.url = new URL("http://"+url.toString());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
        markDirtyProperty();
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
        markDirtyProperty();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        markDirtyProperty();
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        markDirtyProperty();
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
        markDirtyProperty();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        markDirtyProperty();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
        markDirtyProperty();
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
        markDirtyProperty();
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
        markDirtyProperty();
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
        markDirtyProperty();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
        markDirtyProperty();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        markDirtyProperty();
    }

    public String getIndirizzoNoCAP() {
        return indirizzoNoCAP;
    }

    public void setIndirizzoNoCAP(String indirizzoNoCAP) {
        this.indirizzoNoCAP = indirizzoNoCAP;
        markDirtyProperty();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        markDirtyProperty();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
        markDirtyProperty();
    }

    public String getIndirizzoHasNumber() {
        return indirizzoHasNumber;
    }

    public void setIndirizzoHasNumber(String indirizzoHasNumber) {
        this.indirizzoHasNumber = indirizzoHasNumber;
        markDirtyProperty();
    }

    @Override
    public String toString() {
        String s =
                "******************************************************************************************************" + System.getProperties().get("line.separator") +
                "[GeoDocument]{" 
                + "doc_id = " + doc_id 
                + ", url = " + url 
                + ", regione = " +  regione 
                + ", provincia = " + provincia 
                + ", city = " + city 
                + ", indirizzo = " + indirizzo 
                + ", iva = " + iva 
                + ", email = " + email 
                + ", telefono = " + telefono 
                + ", edificio = " + edificio 
                + ", nazione = " + nazione 
                + ", lat = " + lat 
                + ", lng = " + lng 
                + ", description = " + description 
                + ", indirizzoNoCAP = " + indirizzoNoCAP 
                + ", postalCode = " + postalCode 
                + ", fax = " + fax 
                + ", indirizzoHasNumber = " + indirizzoHasNumber 
                + "}" 
                + System.getProperties().get("line.separator")
                + "******************************************************************************************************"  + System.getProperties().get("line.separator");
        //SystemLog.message(0,s);
        return s;
    }
    
    

    


	
    
    
    
    
    
}
