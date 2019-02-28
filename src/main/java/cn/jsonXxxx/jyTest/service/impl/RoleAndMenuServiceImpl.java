package cn.jsonXxxx.jyTest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.entity.RoleMenu;
import cn.jsonXxxx.jyTest.mapper.RoleMapper;
import cn.jsonXxxx.jyTest.mapper.RoleMenuMapper;
import cn.jsonXxxx.jyTest.service.IRoleAndMenuService;

@Service
public class RoleAndMenuServiceImpl implements IRoleAndMenuService {

	private static final Logger logger = LoggerFactory.getLogger(RoleAndMenuServiceImpl.class);

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result insertOrUpdateRole(Role role, List<Long> menuIds) {
		try {
			// 是否对中间表进行覆盖
			Boolean flag = true;
			Long roleId = role.getRoleId();
			// 判断是否为空，为空则是新增
			if (roleId == null) {
				role.setCreateTime(new Date());
				roleMapper.insertOne(role);
				// 插入后返回的主键
				roleId = role.getRoleId();
			} else {
				// 修改的情况
				// 根据roleid查询出所有的menuid
				flag = false;
				roleMapper.updateById(role);
				// 获取中间表的id
				List<RoleMenu> list = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
				// 获取所有的menuid
				List<Long> menuIdlist = list.stream().map(menu -> menu.getId()).collect(Collectors.toList());
				// 如果相同，那么不需要修改
				if (!Objects.equals(menuIds, menuIdlist)) {
					flag = true;
					// 删除中间表所有的
					list.stream().map(menu -> menu.getId()).forEach(id -> {
						roleMenuMapper.deleteById(id);
					});
				}
			}
			// 如果为true，那么重新插入中间表的数据
			if (flag) {
				for (Long menuId : menuIds) {
					roleMenuMapper.insert(new RoleMenu(roleId, menuId));
				}
			}
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("RoleAndMenuServiceImpl-->insertOrUpdateRole" + e.getMessage(), e);
			return Result.ERROR();
		}
	}

}
