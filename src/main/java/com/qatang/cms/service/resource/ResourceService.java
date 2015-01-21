package com.qatang.cms.service.resource;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.form.resource.ResourceForm;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
public interface ResourceService {

	/**
	 *菜单列表
	 * @return
	 */
	public List<Resource> getList();

	/**
	 * 根据菜单id查询
	 * @param id
	 * @return
	 */
	public Resource get(Long id);

	/**
	 * 保存
	 * @param menu
	 * @return
	 */
	public Resource save(Resource menu);

	/**
	 * 修改
	 * @param menu
	 * @return
	 */
	public Resource update(Resource menu);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id);

    /**
     * 查询
     * @param menuForm
     */
    public List<Resource> query(ResourceForm menuForm);

    /**
     * 分页信息
     * */
    public Page<Resource> findAllPage(ResourceForm menuForm);

	/**
	 * 根据userId查询资源
	 * @param userId
	 * @param resourcesType
	 * @return
	 */
	public List<Resource> query(Long userId, ResourcesType resourcesType);
}
