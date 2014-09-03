import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

/**
 * Created by chirowong on 2014/8/7.
 */
public class MyRealm5 implements Realm {
    @Override
    public String getName() {
        return "b"; //realm name 为“a”
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return false;
    }

    //省略supports方法，具体请见源码
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        return new SimpleAuthenticationInfo(
                "zhang", //身份字符串类型
                "123", //凭据
                getName() //Realm Name
        );
    }
}
