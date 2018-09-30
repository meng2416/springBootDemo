package cn.leap.demo.modules.es.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.leap.demo.modules.es.model.Role;
import cn.leap.demo.modules.es.service.RoleService;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author tan123
 * @since 2018-09-18
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
    private RoleService roleService;
 
    @GetMapping(value = "/show")
    public Role testEnum() {
    	Role role = roleService.selectById(1);
        return role;
    }

}

