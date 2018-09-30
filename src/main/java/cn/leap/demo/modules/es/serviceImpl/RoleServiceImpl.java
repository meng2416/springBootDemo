package cn.leap.demo.modules.es.serviceImpl;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.leap.demo.modules.es.dao.RoleDao;
import cn.leap.demo.modules.es.model.Role;
import cn.leap.demo.modules.es.service.RoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author tan123
 * @since 2018-09-18
 */
@Component
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
