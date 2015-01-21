package com.qatang.cms.service.impl.resource;

import com.qatang.cms.dao.resource.ResourceDao;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.form.resource.ResourceForm;
import com.qatang.cms.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likunpeng on 2014/6/24.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> getList() {
		return resourceDao.findAll();
	}

	@Override
	public Resource get(Long id) {
		return resourceDao.findOne(id);
	}

	@Override
	public Resource save(Resource resource) {
		return resourceDao.save(resource);
	}

	@Override
	public Resource update(Resource resource) {
		return resourceDao.save(resource);
	}

	@Override
	public void delete(Long id) {
		resourceDao.delete(id);
	}

    @Override
    public List<Resource> query(ResourceForm resourceForm) {
        List<Resource> resourceList = resourceDao.query(resourceForm);
        if (resourceList != null && resourceList.size() > 0) {
            for (Resource resource : resourceList) {
                List<Resource> secondResourceList = null;
                if (resource.getTreeLevel() == 1 && resource.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                    ResourceForm secondResourceForm = new ResourceForm();
                    secondResourceForm.setTreeLevel("2");
                    secondResourceForm.setParentID(resource.getId() + "");
                    secondResourceList = resourceDao.query(secondResourceForm);
                    if (secondResourceList != null && secondResourceList.size() > 0) {
                        for (Resource secondResource : secondResourceList) {
                            List<Resource> thirdResourceList = null;
                            if (secondResource.getTreeLevel() == 2 && secondResource.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                                ResourceForm thirdResourceForm = new ResourceForm();
                                thirdResourceForm.setTreeLevel("3");
                                thirdResourceForm.setParentID(secondResource.getId() + "");
                                thirdResourceList = resourceDao.query(thirdResourceForm);
                            }
                            if (thirdResourceList != null) {
                                secondResource.setChildren(thirdResourceList);
                            }
                        }
                    }
                    if (secondResourceList != null) {
                        resource.setChildren(secondResourceList);
                    }
                }
            }
        }
        return resourceList;
    }

    @Override
    public Page<Resource> findAllPage(ResourceForm resourceForm) {
        Page<Resource> page = resourceDao.findAllPage(resourceForm);
        List<Resource> resourceList = new ArrayList<Resource>();
        if (page != null) {
            resourceList = page.getContent();
        }
        if (resourceList != null && resourceList.size() > 0) {
            for (Resource resource : resourceList) {
                List<Resource> secondResourceList = null;
                if (resource.getTreeLevel() == 1 && resource.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                    ResourceForm secondResourceForm = new ResourceForm();
                    secondResourceForm.setTreeLevel("2");
                    secondResourceForm.setParentID(resource.getId() + "");
                    secondResourceList = resourceDao.query(secondResourceForm);
                    if (secondResourceList != null && secondResourceList.size() > 0) {
                        for (Resource secondResource : secondResourceList) {
                            List<Resource> thirdResourceList = null;
                            if (secondResource.getTreeLevel() == 2 && secondResource.getHasChildren().getValue() == YesNoStatus.YES.getValue()) {
                                ResourceForm thirdResourceForm = new ResourceForm();
                                thirdResourceForm.setTreeLevel("3");
                                thirdResourceForm.setParentID(secondResource.getId() + "");
                                thirdResourceList = resourceDao.query(thirdResourceForm);
                            }
                            if (thirdResourceList != null) {
                                secondResource.setChildren(thirdResourceList);
                            }
                        }
                    }
                    if (secondResourceList != null) {
                        resource.setChildren(secondResourceList);
                    }
                }
            }
        }
        return page;
    }

    @Override
    public List<Resource> query(Long userId, ResourcesType resourcesType) {
        return resourceDao.query(userId, resourcesType);
    }
}
