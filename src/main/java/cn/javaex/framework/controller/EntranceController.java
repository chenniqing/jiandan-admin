package cn.javaex.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.javaex.framework.basic.exception.QingException;
import cn.javaex.framework.basic.response.Result;
import cn.javaex.framework.model.dto.LoginDTO;
import cn.javaex.framework.model.entity.SysUserEntity;
import cn.javaex.framework.model.vo.SysMenuVO;
import cn.javaex.framework.service.sys_menu.SysMenuService;
import cn.javaex.framework.service.sys_user.SysUserService;
import cn.javaex.htool.crypto.digest.DigestUtils;
import cn.javaex.htool.http.servlet.SessionUtils;

/**
 * 入口
 * 
 * @author 陈霓清
 */
@Controller
public class EntranceController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 跳转主页面
	 * @return
	 */
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	/**
	 * 登录
	 * @param session
	 * @param loginDTO
	 * @return
	 * @throws QingException
	 */
	@PostMapping("/login/submit")
	@ResponseBody
	public Result loginSubmit(HttpSession session, LoginDTO loginDTO) throws QingException {
		// 1. 执行登录判断
		String password = DigestUtils.md5(loginDTO.getPassword());
		SysUserEntity curUser = sysUserService.login(loginDTO.getUsername(), password);
		
		// 2. 查询用户拥有的菜单
		List<SysMenuVO> menuList = sysMenuService.listByUserId(curUser.getId());
		
		session.setMaxInactiveInterval(60*60*2);
		session.setAttribute("curUser", curUser);
		session.setAttribute("menuList", menuList);
		
		return Result.success();
	}
	
	/**
	 * 退出
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 清除session
		session.invalidate();
		
		return "redirect:/login";
	}
	
	/**
	 * 默认首页
	 * @throws QingException 
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index")
	public String index(HttpSession session) throws QingException {
		List<SysMenuVO> menuList = (List<SysMenuVO>) session.getAttribute("menuList");
		
		if (CollectionUtils.isEmpty(menuList)) {
			return "redirect:/403";
		}
		
		return "redirect:" + menuList.get(0).getUrl();
	}
	
	/**
	 * 后台首页
	 * @return
	 */
	@GetMapping("{navUrl}")
	public String menu(HttpServletRequest request, ModelMap map,
			@PathVariable("navUrl") String navUrl) {
		// 选中的导航
		navUrl = "/" + navUrl;
		map.put("navUrl", navUrl);
		
		// 默认显示的菜单链接
		List<SysMenuVO> menuList = SessionUtils.getSession(request, "menuList");
		if (menuList!=null && menuList.isEmpty()==false) {
			for (SysMenuVO sysMenu : menuList) {
				if (navUrl.equals(sysMenu.getUrl())) {
					map.put("navName", sysMenu.getName());
					
					List<SysMenuVO> tempList = sysMenu.getChildren();
					if (tempList!=null && tempList.isEmpty()==false) {
						if (StringUtils.isBlank(tempList.get(0).getUrl())) {
							// 目录
							List<SysMenuVO> tempList2 = tempList.get(0).getChildren();
							if (tempList2!=null && tempList2.isEmpty()==false) {
								map.put("activeUrl", tempList2.get(0).getUrl());
								break;
							}
						} else {
							// 菜单
							map.put("activeUrl", tempList.get(0).getUrl());
							break;
						}
					}
				}
			}
		}
		
		return "common/menu";
	}

}