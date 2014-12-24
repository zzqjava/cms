package com.qatang.cms.controller.resource;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.resource.ResourceForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

/**
 * Created by zhangzq on 2014/12/24.
 */
@Controller
@RequestMapping("/resource")
public class ResourcesController extends BaseController {

    @Autowired
    private IValidator<ResourceForm> resourceValidator;
    @Autowired
    private IValidator<ResourceForm> resourceUpdateValidator;
    @Autowired
    private IValidator<ResourceForm> resourceViewValidator;

    @Autowired
    private ResourceService resourceService;

    //    @RequiresPermissions("sys:resource:list")
    @RequestMapping(value = "/list/{currentPage}", method = RequestMethod.POST)
    public String list(@PathVariable String currentPage, ResourceForm resourceForm, ModelMap modelMap, HttpServletRequest request) {
        if (currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage) < 1) {
            currentPage = 1 + "";
        }
        resourceForm.getPageInfo().setCurrentPage(Integer.parseInt(currentPage));
        pagination(resourceForm, modelMap, request);
        return "resource/resourceList";
    }

    //    @RequiresPermissions("sys:resource:list")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(ModelMap modelMap, HttpServletRequest request) {
        ResourceForm resourceForm = new ResourceForm();
        pagination(resourceForm, modelMap, request);
        return "resource/resourceList";
    }

    private void pagination (ResourceForm resourceForm, ModelMap modelMap, HttpServletRequest request) {
        //只查第一级资源
        resourceForm.setTreeLevel("1");
        resourceForm.setParentID("0");

        Page<Resource> resourcePage = resourceService.findAllPage(resourceForm);
        if (resourcePage != null) {
            if (resourcePage.getContent() != null) {
                List<Resource> resourceList = resourcePage.getContent();
                modelMap.addAttribute(resourceList);
            }
            PageInfo pageInfo = resourceForm.getPageInfo();
            pageInfo.setTotalPages(resourcePage.getTotalPages());
        }
        resourceForm.setTreeLevel("1");
        resourceForm.setParentID("0");
        modelMap.addAttribute(resourceForm);
    }

    //    @RequiresPermissions("sys:resource:input")
    @RequestMapping(value = "/input")
    public String input(ResourceForm resourceForm, ModelMap modelMap) {
        modelMap.addAttribute(resourceForm);
        modelMap.addAttribute(FORWARD_URL, "/resource/list");
        return "/resource/resourceInput";
    }

    //    @RequiresPermissions("sys:resource:input")
    @RequestMapping(value = "/input/{resourceId}", method = RequestMethod.GET)
    public String input(@PathVariable String resourceId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            resourceViewValidator.validate(resourceForm);
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
        return "/resource/resourceInput";
    }

    //    @RequiresPermissions("sys:resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(ResourceForm resourceForm, RedirectAttributes redirectAttributes) {
        try {
            resourceValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            if (resourceForm != null && resourceForm.getId() != null) {
                resourceForm.setId(null);
            }
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute("resourceForm", resourceForm);
            return "redirect:/resource/input";
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
            }
        }
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加资源！");
        return "redirect:/resource/list";
    }

    //   @RequiresPermissions("sys:resource:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ResourceForm resourceForm, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotEmpty(resourceForm.getId())) {
            try {
                resourceUpdateValidator.validate(resourceForm);
            } catch (ValidateFailedException e) {
                logger.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
                redirectAttributes.addFlashAttribute("resourceForm", resourceForm);
                return "redirect:/resource/input";
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

    //    @RequiresPermissions("sys:resource:toggleValidStatus")
    @RequestMapping(value = "/toggleValidStatus/{resourceId}", method = RequestMethod.GET)
    public String toggleValidStatus(@PathVariable String resourceId, RedirectAttributes redirectAttributes) {
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            resourceViewValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/resource/list";
        }
        Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
        if (resource.getValid().getValue() == EnableDisableStatus.ENABLE.getValue()) {
            resource.setValid(EnableDisableStatus.DISABLE);
        } else if (resource.getValid().getValue() == EnableDisableStatus.DISABLE.getValue()) {
            resource.setValid(EnableDisableStatus.ENABLE);
        }
        resourceService.update(resource);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功切换资源的状态！");
        return "redirect:/resource/list";
    }

    //    @RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "/view/{resourceId}", method = RequestMethod.GET)
    public String view(@PathVariable String resourceId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        ResourceForm resourceForm = new ResourceForm();
        resourceForm.setId(resourceId);
        try {
            resourceViewValidator.validate(resourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/resource/list";
        }
        Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
        modelMap.addAttribute(resource);
        modelMap.addAttribute(FORWARD_URL, "/resource/list");
        return "resource/resourceView";
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
