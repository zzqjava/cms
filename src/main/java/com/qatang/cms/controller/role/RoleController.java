package com.qatang.cms.controller.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.PageInfo;
import com.qatang.cms.form.PageUtil;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.validator.IValidator;
import com.qatang.cms.validator.impl.role.RoleFormTypeConverterValidator;
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
import java.util.Date;
import java.util.List;


/**
 * Created by zhangzq on 14-6-25.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IValidator<RoleForm> createRoleValidator;
	@Autowired
	private IValidator<RoleForm> queryRoleValidator;
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFormTypeConverterValidator roleFormTypeConverterValidator;

    @RequiresPermissions("sys:role:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpServletRequest request) {
        RoleForm roleForm = new RoleForm();
        pagination(roleForm, modelMap);
        roleForm.setPageString(PageUtil.getPageString(request, roleForm.getPageInfo()));
        return "role/list";
    }

	@RequiresPermissions("sys:role:list")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(RoleForm roleForm, ModelMap modelMap, HttpServletRequest request) {
        try {
            queryRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "role/list";
        }
        pagination(roleForm, modelMap);
        roleForm.setPageString(PageUtil.getPageString(request, roleForm.getPageInfo()));
        return "role/list";
	}

    @RequiresPermissions("sys:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String input(@ModelAttribute RoleForm roleForm, ModelMap modelMap) {
        //是否默认角色默认为“否”
        roleForm.setIsDefault(String.valueOf(YesNoStatus.NO.getValue()));
        roleForm.setValid(String.valueOf(EnableDisableStatus.ENABLE.getValue()));
        modelMap.addAttribute(roleForm);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "/role/input";
    }

    @RequiresPermissions("sys:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            if (roleForm != null && roleForm.getId() != null) {
                roleForm.setId(null);
            }
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute(roleForm);
            return "redirect:/role/input";
        }
        Role role = new Role();
        role.setName(roleForm.getName());
        role.setIdentifier(roleForm.getIdentifier());
        role.setDescription(roleForm.getDescription());
        role.setIsDefault(YesNoStatus.get(Integer.parseInt(roleForm.getIsDefault())));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        roleService.save(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功添加角色！");
        return "redirect:/role/list";
    }

    @RequiresPermissions("sys:role:update")
    @RequestMapping(value = "/update/{roleId}", method = RequestMethod.GET)
    public String input(@PathVariable String roleId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            redirectAttributes.addFlashAttribute(roleForm);
            return "redirect:/role/list";
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要修改的角色对象不存在！");
        }
        roleForm.setName(role.getName());
        roleForm.setIdentifier(role.getIdentifier());
        roleForm.setDescription(role.getDescription());
        roleForm.setIsDefault(String.valueOf(role.getIsDefault().getValue()));
        roleForm.setValid(String.valueOf(role.getValid().getValue()));
        modelMap.addAttribute(roleForm);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "/role/input";
    }

    @RequiresPermissions("sys:role:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RoleForm roleForm, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try {
            createRoleValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            modelMap.addAttribute(roleForm);
            return "/role/input";
        }
        Role role = roleService.getRole(Long.parseLong(roleForm.getId()));
        if (role == null) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, "要保存的角色对象不存在！");
        }
        role.setName(roleForm.getName());
        role.setIdentifier(roleForm.getIdentifier());
        role.setDescription(roleForm.getDescription());
        role.setIsDefault(YesNoStatus.get(Integer.parseInt(roleForm.getIsDefault())));
        role.setValid(EnableDisableStatus.get(Integer.parseInt(roleForm.getValid())));
        role.setUpdatedTime(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, "成功更新角色！");
        return "redirect:/role/list" ;
    }

    @RequiresPermissions("sys:role:detail")
    @RequestMapping(value = "/detail/{roleId}", method = RequestMethod.GET)
    public String view(@PathVariable String roleId, ModelMap modelMap) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute(ERROR_MESSAGE_KEY, e.getMessage());
            return "redirect:/role/list";
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        modelMap.addAttribute(role);
        modelMap.addAttribute(FORWARD_URL, "/role/list");
        return "role/view";
    }

    @RequiresPermissions("sys:role:validate")
    @RequestMapping(value = "/validate/{roleId}", method = RequestMethod.POST)
    public String validate(@PathVariable String roleId, PrintWriter printWriter) {
        JSONObject rs = new JSONObject();
        RoleForm roleForm = new RoleForm();
        roleForm.setId(roleId);
        try {
            roleFormTypeConverterValidator.validate(roleForm);
        } catch (ValidateFailedException e) {
            logger.error(e.getMessage(), e);
            rs.put("code", "1");
            rs.put("message", e.getMessage());
            printWriter.write(JSON.toJSONString(rs));
            printWriter.flush();
            printWriter.close();
            return null;
        }
        Role role = roleService.getRole(Long.parseLong(roleId));
        if (role == null) {
            rs.put("code", "1");
            rs.put("message", "要切换状态的角色对象不存在！");
            printWriter.write(JSON.toJSONString(rs));
            printWriter.flush();
            printWriter.close();
            return null;
        }
        if (role.getValid().getValue() == EnableDisableStatus.ENABLE.getValue()) {
            role.setValid(EnableDisableStatus.DISABLE);
        } else if (role.getValid().getValue() == EnableDisableStatus.DISABLE.getValue()) {
            role.setValid(EnableDisableStatus.ENABLE);
        }
        rs.put("status",role.getValid().getValue());
        roleService.update(role);
        rs.put("message", "成功切换角色的状态！");
        rs.put("code", "0");
        printWriter.write(JSON.toJSONString(rs));
        printWriter.flush();
        printWriter.close();
        return null;
    }

    private void pagination(RoleForm roleForm, ModelMap modelMap) {
        Page<Role> rolePage = roleService.findAllPage(roleForm);
        if (rolePage.getContent() != null) {
            List<Role> roleList = rolePage.getContent();
            modelMap.addAttribute(roleList);
        }
        PageInfo pageInfo = roleForm.getPageInfo();
        pageInfo.setTotalPages(rolePage.getTotalPages());
        pageInfo.setCount((int)rolePage.getTotalElements());
        roleForm.setPageInfo(pageInfo);
        modelMap.addAttribute(roleForm);
    }

    /**
     * 新增与修改时候使用
     * */
    @ModelAttribute("enableDisableStatus")
    public List<EnableDisableStatus> enableDisableStatus() {
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

    /**
     * 新增与修改时候使用
     * */
    @ModelAttribute("yesNoStatuses")
    public List<YesNoStatus> yesNoStatuses() {
        List<YesNoStatus> yesNoStatuses = YesNoStatus.list();
        return yesNoStatuses;
    }
}
