package com.qatang.cms.form;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @function 分页
 * Created by lkp on 15-01-15.
 */
public class PageUtil {

	public static String getPageString(HttpServletRequest request, PageInfo pageInfo) {
		if (pageInfo.getTotalPages() <= 0) {
			return "暂无分页！";
		}
		StringBuffer sb = new StringBuffer();
		String url = request.getRequestURI().toString();
		//生成js脚本
		sb.append("<script type='text/javascript'>");
		sb.append("function jumpPage(page){");
		sb.append("var patt = /^[1-9]\\d*$/ ;");
		sb.append("if(!patt.test(page)){");
		sb.append("alert('无效操作：请输入正确的数字！');");
		sb.append("return;");
		sb.append("}");
		sb.append("if(page < 1){");
		sb.append("alert('无效操作：页码小于1！');");
		sb.append("return;");
		sb.append("}");
		sb.append("if(page > "+pageInfo.getTotalPages()+"){");
		sb.append("alert('无效操作：页码不能大于"+pageInfo.getTotalPages()+"！');");
		sb.append("return;");
		sb.append("}");
		sb.append("var obj = document.getElementById('formPage');");
		sb.append("obj.value = page;");
		sb.append("document.abstractPagingForm.submit();");
		sb.append("}");
		sb.append("function changePage(page){");
		sb.append("$('.formPageInput').val(page);");
		sb.append("}");
		sb.append("function jumpPageSub(){");
		sb.append("var obj = document.getElementById('formPage');");
		sb.append("jumpPage(obj.value);");
		sb.append("}");
		sb.append("</script>");
		//生成form
		sb.append("<form style='margin:0;padding:0;clear:both;' name='abstractPagingForm' action='"+url+"' method='post'>");
		sb.append("共有").append("<span style='color:#EC8722;'>").append(pageInfo.getTotalPages()).append("</span>").append("页");
		sb.append("&nbsp;<span style='color:#EC8722;'>").append(pageInfo.getCount()).append("</span>").append("条记录");
		sb.append("&nbsp;第");
		sb.append("<select onchange='jumpPage(this.value)'>");
		int showPageCount = 0;
		if(pageInfo.getTotalPages() > 100){
			showPageCount = 100;
		}else{
			showPageCount = pageInfo.getTotalPages();
		}
		for (int i = 1; i <= showPageCount; i++) {
			if(i == pageInfo.getCurrentPage()){
				sb.append("<option value='").append(i).append("' selected>").append(i).append("</option>");
			}else{
				sb.append("<option value='").append(i).append("'>").append(i).append("</option>");
			}
		}
		sb.append("</select>");
		sb.append("页");
		sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage(1);'>首页</a>");
		if(pageInfo.getCurrentPage() > 1){
			sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage("+(pageInfo.getCurrentPage() - 1)+");'>上一页</a>");
		}
		if(pageInfo.getCurrentPage() < pageInfo.getCurrentPage()){
			sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage("+(pageInfo.getCurrentPage() + 1)+");'>下一页</a>");
		}
		sb.append("&nbsp;跳转到第");
		//生成参数隐藏域
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String element = (String) parameterNames.nextElement();
			if(element.indexOf("pageInfo.currentPage") > -1) {
				continue;
			}
			if (request.getParameterValues(element).length > 1) {
				for (int i = 0;i < request.getParameterValues(element).length;i++) {
					sb.append("<input type='hidden' name='"+element+"' value='"+request.getParameterValues(element)[i]+"'>");
				}
			} else {
				sb.append("<input type='hidden' name='"+element+"' value='"+request.getParameter(element)+"'>");
			}
			
		}
		
		sb.append("<input name='pageInfo.currentPage' id='formPage' class='formPageInput' onchange='changePage(this.value)' type='text' style='width:40px;' value='"+ pageInfo.getCurrentPage() +"' />页");
		sb.append("<button class=\"btn btn-sm btn-default\" onclick=\"jumpPageSub()\">GO</button>");
		sb.append("</form>");
		
		return sb.toString();
	}

	public static String getSimplePageString(PageInfo pageInfo){
		if (pageInfo.getTotalPages() <= 0) {
			return "暂无分页！";
		}
		StringBuffer sb = new StringBuffer();
		//生成form
		sb.append("共有").append("<span style='color:#EC8722;'>").append(pageInfo.getTotalPages()).append("</span>").append("页");
		sb.append("&nbsp;<span style='color:#EC8722;'>").append(pageInfo.getCount()).append("</span>").append("条记录");
		sb.append("&nbsp;第");
		sb.append("<select onchange='jumpPage(this.value)'>");
		int showPageCount = 0;
		if(pageInfo.getTotalPages()  > 100){
			showPageCount = 100;
		}else{
			showPageCount = pageInfo.getTotalPages() ;
		}
		for (int i = 1; i <= showPageCount; i++) {
			if(i == pageInfo.getCurrentPage()){
				sb.append("<option value='").append(i).append("' selected>").append(i).append("</option>");
			}else{
				sb.append("<option value='").append(i).append("'>").append(i).append("</option>");
			}
		}
		sb.append("</select>");
		sb.append("页");
		sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage(1);'>首页</a>");
		if(pageInfo.getCurrentPage() > 1){
			sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage("+(pageInfo.getCurrentPage()  - 1)+");'>上一页</a>");
		}
		if(pageInfo.getCurrentPage()  < pageInfo.getCurrentPage() ){
			sb.append("&nbsp;<a style='color:#EC8722' href='javascript:jumpPage("+(pageInfo.getCurrentPage()  + 1)+");'>下一页</a>");
		}
		sb.append("&nbsp;跳转到第");
		sb.append("<input name='pageBean.page' class='formPageInput' onchange='changePage(this.value)' type='text' style='width:40px;' value='"+pageInfo.getCurrentPage() +"' />页");
		sb.append("<input type='button' onclick='jumpPageSub()' value=' GO ' />");
		
		return sb.toString();
	}
}
