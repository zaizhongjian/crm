package cn.jsonXxxx.jyTest.utils;

public enum LoginType {

    PC("pc"), WECHAT("wechat");

    private String loginType;

    LoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
