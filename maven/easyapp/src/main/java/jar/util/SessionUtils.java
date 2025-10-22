package jar.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/2 13:32
 */
public class SessionUtils {
    private static final String USER_KEY = "_$USER_";

    public static void setUser(Object user) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        requestAttributes.setAttribute(USER_KEY, user, RequestAttributes.SCOPE_SESSION);
    }

    public static <T> T getUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (T) requestAttributes.getAttribute(USER_KEY, RequestAttributes.SCOPE_SESSION);
    }
}
