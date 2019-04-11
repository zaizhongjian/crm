package cn.jsonXxxx.jyTest.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
    public MyHashedCredentialsMatcher() {
    }

    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUsernamePasswordToken uToken = (MyUsernamePasswordToken) token;
        if (uToken.getLoginType().equals("pc")) {
            return super.doCredentialsMatch(token, info);
        } else {
            return true;
        }
    }
}
