package com.qatang.cms.dao.resource;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.form.resource.ResourceForm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
public interface ResourceDao extends JpaRepository<Resource, Long> {

	/**
	 * 查询
	 * @param resourceForm
	 */
	public List<Resource> query(ResourceForm resourceForm);

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