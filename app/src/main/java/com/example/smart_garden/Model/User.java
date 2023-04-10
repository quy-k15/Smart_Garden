package com.example.smart_garden.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    private String email;
    private String user_id;
    private String username;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String city;



    public User() {
        address = "";
        email = "";
        user_id = "";
        username = "";

        birthday = "";
        phoneNumber = "";
        city = "";

    }

    protected User(Parcel in) {
        email = in.readString();
        user_id = in.readString();
        username = in.readString();

        birthday = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
        city = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public User(String email, String user_id, String username, String dateOfBirth, String phoneNumber, String address, String city ) {
        this.email = email;
        this.user_id = user_id;
        this.username = username;

        this.birthday = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +

                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(user_id);
        dest.writeString(username);
        dest.writeString(birthday);
        dest.writeString(phoneNumber);
        dest.writeString(address);
        dest.writeString(city);

    }
}

