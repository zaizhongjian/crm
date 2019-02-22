package cn.jsonXxxx.jyTest.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.jsonXxxx.jyTest.shiro.UserRealm;
import net.sf.ehcache.CacheManager;

@Configuration
public class ShiroConfiguration {

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public UserRealm myShiroRealm() {
		UserRealm myShiroRealm = new UserRealm();
		myShiroRealm.setCachingEnabled(true);
		myShiroRealm.setCacheManager(ehCacheManager());
		myShiroRealm.setAuthorizationCachingEnabled(true);
		myShiroRealm.setAuthorizationCacheName("authorizationCache");
		myShiroRealm.setAuthenticationCachingEnabled(true);
		myShiroRealm.setAuthenticationCacheName("authenticationCache");
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		// ShiroFilterFactoryBean shiroFilterFactoryBean = new
		// ShiroFilterFactoryBean();
		// shiroFilterFactoryBean.setSecurityManager(securityManager);
		// Map<String,String> map = new HashMap<String, String>();
		// map.put("/user/login","authc");
		// map.put("/**/**", "anon");
		// shiroFilterFactoryBean.setLoginUrl("/user/login");

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 设置login URL
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		//shiroFilterFactoryBean.setSuccessUrl("/login/index");
		// 未授权的页面
		// shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.action");
		// src="jquery/jquery-3.2.1.min.js" 生效
		filterChainDefinitionMap.put("/jquery/*", "anon");
		// 设置登录的URL为匿名访问，因为一开始没有用户验证
		filterChainDefinitionMap.put("/login/*", "anon");

		filterChainDefinitionMap.put("/Exception.class", "anon");
		// 我写的url一般都是xxx.action，根据你的情况自己修改
		// filterChainDefinitionMap.put("/*.action", "authc");
		// 退出系统的过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		// 现在资源的角色
		// filterChainDefinitionMap.put("/admin.html", "roles[admin]");
		// filterChainDefinitionMap.put("/user.html", "roles[user]");
		// 最后一班都，固定格式
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 注入自定义的realm;
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}

	@Bean
	public EhCacheManager ehCacheManager() {
		CacheManager cacheManager = CacheManager.getCacheManager("myEhcache");
		if (cacheManager == null) {
			cacheManager = CacheManager.create();
		}
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;
	}

}
