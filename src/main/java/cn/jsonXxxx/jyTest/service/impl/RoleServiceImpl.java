package cn.jsonXxxx.jyTest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.mapper.RoleMapper;
import cn.jsonXxxx.jyTest.query.BaseQuery;
import cn.jsonXxxx.jyTest.service.IRoleService;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleMapper mapper;

	@Override
	public PageList<Role> findAll(BaseQuery query) {
		// 分页插件
		IPage<Role> page = new Page<Role>(query.getPage(), query.getLimit());
		String key = query.getKey();
		// 模糊匹配条件封装
		QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
		if (StringUtils.isNotBlank(key)) {
			queryWrapper.like("role_id", key).or().like("role_name", key).or().like("remark", key).or().like("dept_id",
					key);
		}
		IPage<Role> selectPage = null;
		// 封装成layui的模板
		PageList<Role> pageList = new PageList<Role>();
		try {
			selectPage = mapper.selectPage(page, queryWrapper);
			pageList.setCount(selectPage.getTotal());
			pageList.setData(selectPage.getRecords());
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
			pageList.setMsg("操作失败");
			logger.error("RoleServiceImpl-->findAll" + e.getMessage(), e);
			return pageList;
		}

	}

}
