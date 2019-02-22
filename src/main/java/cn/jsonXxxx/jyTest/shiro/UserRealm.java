package cn.jsonXxxx.jyTest.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import cn.jsonXxxx.jyTest.config.MySimpleByteSource;
import cn.jsonXxxx.jyTest.entity.Menu;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.service.IUserService;

@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.getByUsername(username);
		List<Role> roles = user.getRoles();
		Set<String> roleNames = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		for (Role role : roles) {
			roleNames.add(role.getRoleName());
			List<Menu> menus = role.getMenus();
			menus.stream().forEach(menu -> {
				String perms = menu.getPerms();
				if (StringUtils.isNotBlank(perms)) {
					if (perms.contains(",")) {
						String[] split = perms.split(",");
						for (String str : split) {
							permissions.add(str);
						}
					} else {
						permissions.add(perms);
					}
				}
			});
		}
		authorizationInfo.setRoles(roleNames);
		authorizationInfo.setStringPermissions(permissions);
		System.err.println(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getByUsername(username);
		if (Objects.isNull(user)) {
			throw new UnknownAccountException();
		}
		String string = getName();
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), new MySimpleByteSource(user.getSalt().getBytes()), string);
		return authenticationInfo;
	}

}
