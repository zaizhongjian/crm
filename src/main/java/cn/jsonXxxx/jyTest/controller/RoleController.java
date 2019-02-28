package cn.jsonXxxx.jyTest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.entity.SelectData;
import cn.jsonXxxx.jyTest.entity.SelectList;
import cn.jsonXxxx.jyTest.query.BaseQuery;
import cn.jsonXxxx.jyTest.service.IRoleAndMenuService;
import cn.jsonXxxx.jyTest.service.IRoleService;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService service;

	@Autowired
	private IRoleAndMenuService roleAndMenuService;

	@RequestMapping("/list")
	public PageList<Role> findAllByQuery(BaseQuery query) {
		return service.findAll(query);
	}

	/**
	 * 根据id删除角色
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/deleteOne")
	public Result deletOne(Long roleId) {
		if (roleId == null) {
			return Result.ERROR().addMsg("角色id不能为空");
		}
		try {
			service.removeById(roleId);
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("RoleController-->deletOne" + e.getMessage(), e);
			return Result.ERROR().addMsg("操作失败");
		}
	}

	/**
	 * 批量删除角色
	 * 
	 * @param roleIds
	 * @return
	 */
	@PostMapping("/deleteAll")
	public Result deleteAll(@RequestParam(name = "roleIds[]") Long roleIds[]) {
		List<Long> asList = Arrays.asList(roleIds);
		if (CollectionUtils.isEmpty(asList)) {
			return Result.ERROR().addMsg("数值不能为空");
		}
		try {
			service.removeByIds(asList);
			return Result.SUCCESS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("RoleController-->deleteAll" + e.getMessage(), e);
			return Result.ERROR();
		}
	}

	@RequestMapping("/insertOrUpdate")
	public Result insertOrUpdate(Role role, @RequestParam(name = "menuIds[]") Long menuIds[]) {
		List<Long> asList = Arrays.asList(menuIds);
		if (Objects.isNull(role) || CollectionUtils.isEmpty(asList)) {
			return Result.ERROR().addMsg("角色或权限id为空");
		}
		return roleAndMenuService.insertOrUpdateRole(role, asList);
	}

	@RequestMapping("/selectList")
	public SelectList selectList() {
		SelectList selectList = new SelectList();
		List<SelectData> selectDateList = new ArrayList<SelectData>();
		List<Role> list = null;
		try {
			list = service.list(new QueryWrapper<Role>());
			list.stream().forEach(role -> {
				SelectData selectData = new SelectData();
				selectData.setName(role.getRoleName());
				selectData.setValue(role.getRoleId());
				selectDateList.add(selectData);
			});
			selectList.setData(selectDateList);
			selectList.setCode(0);
			selectList.setMsg("success");
			return selectList;
		} catch (Exception e) {
			selectList.setData(null);
			selectList.setCode(1);
			selectList.setMsg("fail");
			e.printStackTrace();
			logger.error("RoleController-->selectList" + e.getMessage(), e);
			return selectList;
		}
	}
}
