package cn.leap.demo.modules.role.controller;


import cn.leap.demo.modules.role.model.Role;
import cn.leap.demo.modules.role.serviceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RoleServiceImpl roleServiceImpl;
 
    @GetMapping(value = "/show")
    public Role testEnum() {
    	return roleServiceImpl.selectById(1);
    }

}

