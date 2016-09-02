package com.example.thepolicykart2;

public class ApplyOnlineData {
    String premium;

    String firstname,lastname,mobile,email,city,address,pincode,age,gender,plan,ifreceivenews;
    public ApplyOnlineData(String premium,String firstname, String lastname, String mobile, String email, String city, String address, String
                           pincode, String age, String gender, String plan, String ifreceivenews)
    {
        this.premium=premium;
        this.firstname=firstname;
        this.lastname=lastname;
        this.mobile=mobile;
        this.email=email;
        this.city=city;
        this.address=address;
        this.pincode=pincode;
        this.age=age;
        this.gender=gender;
        this.plan=plan;
        this.ifreceivenews=ifreceivenews;
    }
}
