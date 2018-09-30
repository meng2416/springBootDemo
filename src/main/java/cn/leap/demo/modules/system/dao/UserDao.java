package cn.leap.demo.modules.system.dao;

import java.util.List;

import cn.leap.demo.modules.system.model.User;

/**
 * 
 * dao层
 *
 */
public interface UserDao {

	public User get(User user);

	public List<User> getUserList(User user);

	public User loginCheck(User user);

	public int insert(User user);

}
