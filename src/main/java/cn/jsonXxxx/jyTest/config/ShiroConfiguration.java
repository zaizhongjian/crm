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

	/**
	 * 自定义realm
	 *
	 * @return
	 */

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

	/**
	 * 安全管理器 注：使用shiro-spring-boot-starter
	 * 1.4时，返回类型是SecurityManager会报错，直接引用shiro-spring则不报错
	 *
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	/**
	 * 设置过滤规则
	 *
	 * @param securityManager
	 * @return
	 */
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
		shiroFilterFactoryBean.setLoginUrl("/login/tologin");
		shiroFilterFactoryBean.setSuccessUrl("/login/toindex");
		filterChainDefinitionMap.put("/login/**", "anon");
		filterChainDefinitionMap.put("/public/**", "anon");

		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/json/**", "anon");

		// 设置登录的URL为匿名访问，因为一开始没有用户验证
		filterChainDefinitionMap.put("/Exception.class", "anon");
		// 退出系统的过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		// 最后一班都，固定格式
		filterChainDefinitionMap.put("/**/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 开启shiro aop注解支持 使用代理方式;所以需要开启代码支持;
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 注入自定义的realm;
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * 凭证匹配器
	 *
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}

	/*
	 * shiro缓存管理器;
	 * 需要注入对应的其它的实体类中-->安全管理器：securityManager可见securityManager是整个shiro的核心；
	 */
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
