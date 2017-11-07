package com.kantapp.retrofittutorial;

/**
 * Created by Vinayak on 11/6/2017.
 */

public class Movie {


    String firstname, lastname ,age ;
    int _id;

    public Movie(){

    }
    public Movie(int _id, String FirstName, String LastName){
        this._id = _id;
        this.firstname = FirstName;
        this.lastname = LastName;
    }

    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getage() {
        return age;
    }

    public void setage(String age) {
        this.age = age;
    }


}
