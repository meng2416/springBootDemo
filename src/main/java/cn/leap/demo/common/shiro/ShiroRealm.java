package cn.leap.demo.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.leap.demo.modules.system.model.User;
import cn.leap.demo.modules.system.service.UserService;

/**
 * shiro用户登入认证
 */
public class ShiroRealm extends AuthorizingRealm {

    public ShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }
    @Autowired
    private UserService userService;

    public ShiroRealm() {
        super();
    }
    //用户登入认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = String.valueOf(authenticationToken.getPrincipal());
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(password);
        User user1 = userService.loginCheck(user);
        if(null == user1 || user1.getState() == 0){
            throw new UnknownAccountException(String.format("账号[%s]或密码错误!", userName));
        }
        if(user1.getState() == 2){
            throw new AuthenticationException (String.format("用户[%s]被冻结!", userName));
        }
        ByteSource salt = ByteSource.Util.bytes(userName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user1, user1.getPassword(), salt, getName());
        return authenticationInfo;
    }

   //用户权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //Long userId = TokenManager.getUserId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        //Set<String> roles =  userService.selectRoleIdByUserId(userId);
        //info.setRoles(roles);
        //根据用户ID查询权限（permission），放入到Authorization里。
        return info;
    }
}
