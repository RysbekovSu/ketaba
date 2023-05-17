package com.Ketaba.ketaba.models;


import javax.persistence.*;


@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title,photo,type,region,bed, services, email, number, address, map,price;

    private int capacity,size;
    public String stPrice= String.valueOf(price);

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getPhoto(){
        return photo;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region=region;
    }
    public String getBed(){
        return bed;
    }
    public void setBed(String bed){
        this.bed=bed;
    }
    public String getServices(){
        return services;
    }
    public void setServices(String services){
        this.services=services;
    }

    public String getMap(){
        return map;
    }
    public void setMap(String map){
        this.map=map;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Hotel(String title,String photo,String type,String region,String bed,String services,String price,int capacity,int size,String email,String number, String address, String map) {
        this.photo = photo;
        this.type = type;
        this.region = region;
        this.bed = bed;
        this.services = services;
        this.price = price;
        this.capacity = capacity;
        this.size = size;
        this.title = title;
        this.email=email;
        this.address=address;
        this.number=number;
        this.map=map;
    }

    public Hotel() {
    }






}
