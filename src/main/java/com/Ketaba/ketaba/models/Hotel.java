package com.Ketaba.ketaba.models;


import javax.persistence.*;


@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title,type,region,bed, services, email, number, address, map,price;

    private String photo1,photo2,photo3,photo4,photo5,photo6,description;
    private int capacity,size;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


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
    public String getPhoto1(){
        return photo1;
    }
    public void setPhoto1(String photo1){
        this.photo1=photo1;
    }

    public String getPhoto2(){
        return photo2;
    }
    public void setPhoto2(String photo2){
        this.photo2=photo2;
    }

    public String getPhoto3(){
        return photo3;
    }
    public void setPhoto3(String photo3){
        this.photo3=photo3;
    }
    public String getPhoto4(){
        return photo4;
    }
    public void setPhoto4(String photo4){
        this.photo4=photo4;
    }
    public String getPhoto5(){
        return photo5;
    }
    public void setPhoto5(String photo5){
        this.photo5=photo5;
    }
    public String getPhoto6(){
        return photo6;
    }
    public void setPhoto6(String photo6){
        this.photo6=photo6;
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

    public Hotel(String title,String photo1,String type,String region,String bed,String services,String price,int capacity,int size,String email,String number, String address, String map,
                 String photo2,String photo3,String photo4,String photo5,String photo6,String description) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.photo6 = photo6;
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
        this.description=description;
    }

    public Hotel() {
    }






}
