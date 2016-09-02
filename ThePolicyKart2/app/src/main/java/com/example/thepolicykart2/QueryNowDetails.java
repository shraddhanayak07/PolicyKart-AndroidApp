package com.example.thepolicykart2;

public class QueryNowDetails {

    String firstname,lastname,mobile,email,policyn,comments,ifreceivenews;

    QueryNowDetails(String firstname,String lastname,String mobile, String email, String policyn,String comments,
                    String ifreceivenews)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.mobile=mobile;
        this.email=email;
        this.policyn=policyn;
        this.comments=comments;
        this.ifreceivenews=ifreceivenews;
    }
}
