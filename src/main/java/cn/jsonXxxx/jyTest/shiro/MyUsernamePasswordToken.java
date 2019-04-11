package cn.jsonXxxx.jyTest.shiro;

import cn.jsonXxxx.jyTest.utils.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken {
    private LoginType loginType;

    public MyUsernamePasswordToken(String username, String password) {
        super(username, password);
        this.loginType = LoginType.PC;
    }

    public MyUsernamePasswordToken(String openId) {
        super(openId, "", false, null);
        this.loginType = LoginType.WECHAT;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
