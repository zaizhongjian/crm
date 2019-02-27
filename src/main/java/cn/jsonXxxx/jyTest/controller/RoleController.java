package cn.jsonXxxx.jyTest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.entity.SelectData;
import cn.jsonXxxx.jyTest.entity.SelectList;
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
