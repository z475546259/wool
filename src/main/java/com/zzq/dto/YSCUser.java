package com.zzq.dto;

public class YSCUser {
    String userPhone;
    String userPassWord;

    public YSCUser(String userPhone, String userPassWord) {
        this.userPhone = userPhone;
        this.userPassWord = userPassWord;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserPhone() {
        return userPhone;

    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
