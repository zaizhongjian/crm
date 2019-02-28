package cn.jsonXxxx.jyTest.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.query.BaseQuery;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface IRoleService extends IService<Role> {
	PageList<Role> findAll(BaseQuery query);
}
