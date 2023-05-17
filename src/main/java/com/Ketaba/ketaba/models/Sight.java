package com.Ketaba.ketaba.models;

import javax.persistence.*;
@Entity
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, description1, place, description2, address;
    private String photo1,photo2,photo3,photo4;

    public String getPhoto1() {
        return photo1;
    }
    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }
    public void setPhoto2(String photo1) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }
    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }
    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription1(){
        return description1;
    }
    public void setDescription1(String description1) {
        this.description1 = description1;
    }
    public String getDescription2(){
        return description2;
    }
    public void setDescription2(String description2) {
        this.description2 = description2;
    }
    public Sight(String title,String description1,String description2,String place,String photo1,String photo2,String photo3,String photo4,String address) {
        this.description1 = description1;
        this.description2 = description2;

        this.place = place;
        this.title = title;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.address = address;
    }
    public Sight() {
    }


}
