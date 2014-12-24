package com.qatang.cms.controller.role;

import com.alibaba.fastjson.JSON;
import com.qatang.cms.beans.ZtreeBean;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.RoleResource;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleResourceForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.service.role.RoleResourceService;
import com.qatang.cms.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@RequestMapping("/allotResource")
public class RoleResourceController extends BaseController {
    @Autowired
    private IValidator<RoleResourceForm> createRoleResourceValidator;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private ResourceService resourceService;

    /*@RequiresPermissions("sys:allotResource:input")*/
    @RequestMapping(value = "/input/{roleId}", method = RequestMethod.GET)
    public String allotResource(@PathVariable Long roleId, Model model, RedirectAttributes redirectAttributes) {
        if (roleId == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "传入的角色id为null！");
            return "redirect:/role/list" ;
        }
        //所有权限
        List<Resource> resourceList = resourceService.getList();
        //拥有权限
        List<RoleResource> roleResourceList = roleResourceService.findByRoleId(roleId);
        List<Long> ownResourceList = new ArrayList<>();
        for(RoleResource rm : roleResourceList){
            ownResourceList.add(rm.getResourceId());
        }

        List<ZtreeBean> ztreeBeanList = new ArrayList<>();
        for(Resource resource : resourceList){
            boolean checked = false;
            if(ownResourceList.contains(resource.getId())){
                checked = true;
            }
            ZtreeBean ztreeBean = new ZtreeBean();
            ztreeBean.setId(resource.getId()+"");
            ztreeBean.setpId(resource.getParentID() + "");
            ztreeBean.setName(resource.getName());
            ztreeBean.setChecked(checked);
            ztreeBeanList.add(ztreeBean);
        }
        String ztrees = JSON.toJSONString(ztreeBeanList);
        model.addAttribute("ztrees",ztrees);
        model.addAttribute("roleId",roleId);
        return "/role/allotResource";
    }

    /*@RequiresPermissions("sys:role:createRoleResource")*/
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String manageAllotResource(RoleResourceForm roleResourceForm, Model model, RedirectAttributes redirectAttributes) {
        try {
            createRoleResourceValidator.validate(roleResourceForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            model.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            model.addAttribute(model);
            return "/role/allotResource";
        }
        String resourceIds = roleResourceForm.getResourceIds();
        Long roleId = roleResourceForm.getRoleId();
        List<RoleResource> roleResources = new ArrayList<>();
        String[] resourceStrArray = resourceIds.split(",");
        for(String resourceStr : resourceStrArray){
            RoleResource roleResource = new RoleResource();
            Long resourceId = Long.valueOf(resourceStr);
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceId);
            roleResources.add(roleResource);
        }
        roleResourceService.save(roleId,roleResources);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "分配资源成功！");
        return "redirect:/role/list" ;
    }
}
