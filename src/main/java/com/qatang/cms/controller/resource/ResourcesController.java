package com.qatang.cms.controller.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.PageUtil;
import com.qatang.cms.form.resource.ResourceForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by zhangzq on 2014/12/24.
 */
@Controller
@RequestMapping("/resource")
public class ResourcesController extends BaseController {

    @Autowired
    private IValidator<ResourceForm> createResourceValidator;
    @Autowired
    private IValidator<ResourceForm> updateResourceValidator;
    @Autowired
    private IValidator<ResourceForm> detailResourceValidator;
    @Autowired
    private IValidator<ResourceForm> queryResourceValidator;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("sys:resource:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpServletRequest request) {
        ResourceForm resourceForm = new ResourceForm();
        //只查第一级资源
        pagination(resourceForm, modelMap);
        resourceForm.setPageString(PageUtil.getPageString(request, resourceForm.getPageInfo()));
        return "resource/list";
    }

    @RequiresPermissions("sys:resource:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(ResourceForm resourceForm, ModelMap modelMap, HttpServletRequest request) {
        try {
            queryResourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "resource/list";
        }
        pagination(resourceForm, modelMap);
        resourceForm.setPageString(PageUtil.getPageString(request, resourceForm.getPageInfo()));
        return "resource/list";
    }

    private void pagination(ResourceForm resourceForm, ModelMap modelMap) {
        Page<Resource> resourcePage = resourceService.findAllPage(resourceForm);
        if (resourcePage != null && resourcePage.getContent() != null) {
            List<Resource> resourceList = resourcePage.getContent();
            modelMap.addAttribute(resourceList);
            PageInfo pageInfo = resourceForm.getPageInfo();
            pageInfo.setTotalPages(resourcePage.getTotalPages());
            pageInfo.setCount((int)resourcePage.getTotalElements());
            resourceForm.setPageInfo(pageInfo);
        }
        modelMap.addAttribute(resourceForm);
    }

    @RequiresPermissions("sys:resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ResourceForm resourceForm, ModelMap modelMap) {
        if (resourceForm == null) {
            resourceForm = new ResourceForm();
        }
        modelMap.addAttribute(resourceForm);
        modelMap.addAttribute(FORWARD_URL, "/resource/list");
        return "/resource/create";
    }

    @RequiresPermissions("sys:resource:update")
    @RequestMapping(value = "/update/{resourceId}", method = RequestMethod.GET)
    public String update(@PathVariable String resourceId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            detailResourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/resource/list";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/resource/list";
        }

        Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
        resourceForm.setName(resource.getName());
        resourceForm.setUrl(resource.getUrl());
        resourceForm.setPriority(resource.getPriority() + "");
        resourceForm.setValidValue(resource.getValid().getValue() + "");
        resourceForm.setMemo(resource.getMemo());
        resourceForm.setTreeLevel(resource.getTreeLevel() + "");
        resourceForm.setType(resource.getType().getValue() + "");
        resourceForm.setParentID(resource.getParentID() + "");
        resourceForm.setIdentifier(resource.getIdentifier());
        modelMap.addAttribute(resourceForm);
        modelMap.addAttribute(FORWARD_URL, "/resource/list");
        return "/resource/update";
    }

    @RequiresPermissions("sys:resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(ResourceForm resourceForm, RedirectAttributes redirectAttributes) {
        try {
            createResourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            if (resourceForm != null && resourceForm.getId() != null) {
                resourceForm.setId(null);
            }
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute("resourceForm", resourceForm);
            return "redirect:/resource/create";
        }
        Resource resource = new Resource();
        resource.setName(resourceForm.getName());
        resource.setUrl(resourceForm.getUrl());
        resource.setPriority(Integer.valueOf(resourceForm.getPriority()));
        resource.setValid(EnableDisableStatus.get(Integer.valueOf(resourceForm.getValidValue())));
        resource.setMemo(resourceForm.getMemo());
        resource.setCreatedTime(new Date());
        resource.setUpdatedTime(new Date());

        resource.setType(ResourcesType.get(Integer.valueOf(resourceForm.getType())));
        resource.setParentID(StringUtils.isNotEmpty(resourceForm.getParentID()) ? Long.parseLong(resourceForm.getParentID()) : 0);
        resource.setTreeLevel(StringUtils.isNotEmpty(resourceForm.getTreeLevel()) ? Integer.parseInt(resourceForm.getTreeLevel()) : 0);
        resource.setIdentifier(resourceForm.getIdentifier());
        resource.setHasChildren(YesNoStatus.NO);
        resource = resourceService.save(resource);

        //如果不是第一级菜单时
        if (resource != null && resource.getTreeLevel() != 1) {
            //查询相应的父级 然后修改
            Resource parentResource = resourceService.get(resource.getParentID());
            if (parentResource != null) {
                parentResource.setHasChildren(YesNoStatus.YES);
                resourceService.update(parentResource);
                resource.setPath(parentResource.getPath() + Resource.SPRIT + resource.getId());
                resourceService.update(resource);
            }
        } else {
            resource.setPath(Resource.SPRIT + resource.getId());
            resourceService.update(resource);
        }
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加资源！");
        return "redirect:/resource/list";
    }

    @RequiresPermissions("sys:resource:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ResourceForm resourceForm, RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotEmpty(resourceForm.getId())) {
            try {
                updateResourceValidator.validate(resourceForm);
            } catch (ValidateFailedException e) {
                logger.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
                redirectAttributes.addFlashAttribute("resourceForm", resourceForm);
                return "redirect:/resource/update/" + resourceForm.getId();
            }
            Long id = Long.parseLong(resourceForm.getId());
            Resource resource = resourceService.get(id);
            resource.setName(resourceForm.getName());
            resource.setUrl(resourceForm.getUrl());
            resource.setPriority(Integer.valueOf(resourceForm.getPriority()));
            resource.setValid(EnableDisableStatus.get(Integer.valueOf(resourceForm.getValidValue())));
            resource.setMemo(resourceForm.getMemo());
            resource.setUpdatedTime(new Date());
            resource.setType(ResourcesType.get(Integer.valueOf(resourceForm.getType())));
            resource.setIdentifier(resourceForm.getIdentifier());
            resourceService.update(resource);
        }
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功更新资源！");
        return "redirect:/resource/list" ;
    }

    @RequiresPermissions("sys:resource:switchStatus")
    @RequestMapping(value = "/switchStatus/{resourceId}", method = RequestMethod.POST)
    public String switchStatus(@PathVariable String resourceId,  PrintWriter printWriter) {
        JSONObject rs = new JSONObject();
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            detailResourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            rs.put("code", "1");
            rs.put("message", e.getMessage());
            printWriter.write(JSON.toJSONString(rs));
            printWriter.flush();
            printWriter.close();
            return null;
        }
        Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
        if (resource.getValid().getValue() == EnableDisableStatus.ENABLE.getValue()) {
            resource.setValid(EnableDisableStatus.DISABLE);
        } else if (resource.getValid().getValue() == EnableDisableStatus.DISABLE.getValue()) {
            resource.setValid(EnableDisableStatus.ENABLE);
        }
        rs.put("status",resource.getValid().getValue());
        resourceService.update(resource);
        rs.put("message", "成功切换资源的状态！");
        rs.put("code", "0");
        printWriter.write(JSON.toJSONString(rs));
        printWriter.flush();
        printWriter.close();
        return null;
    }

    @RequiresPermissions("sys:resource:detail")
    @RequestMapping(value = "/detail/{resourceId}", method = RequestMethod.GET)
    public String detail(@PathVariable String resourceId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            detailResourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/resource/list";
        }
        Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
        modelMap.addAttribute(resource);
        modelMap.addAttribute(FORWARD_URL, "/resource/list");
        return "resource/detail";
    }

    /**
     * 新增修改时候使用
     * */
    @ModelAttribute("enableDisableStatus")
    public List<EnableDisableStatus> enableDisableItems() {
        List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.list();
        return enableDisableStatus;
    }
    /**
     * 查询时候使用
     * */
    @ModelAttribute("queryEnableDisableStatus")
    public List<EnableDisableStatus> queryEnableDisableStatus() {
        List<EnableDisableStatus> enableDisableStatus = EnableDisableStatus.listAll();
        return enableDisableStatus;
    }
    @ModelAttribute("resourcesTypeItems")
    public List<ResourcesType> resourcesTypeItems() {
        List<ResourcesType> resourcesTypeItems = ResourcesType.list();
        return resourcesTypeItems;
    }

}
