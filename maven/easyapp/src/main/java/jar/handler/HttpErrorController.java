package jar.handler;

import jar.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/2 13:54
 */
@Slf4j
@RestController
public class HttpErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(path = ERROR_PATH)
    public Result error(HttpServletRequest request, HttpServletResponse response) {
        log.error("访问{}, 错误代码：{}", ERROR_PATH, response.getStatus());
        Result result = new Result();
        result.setCode(response.getStatus());
        result.setMessage(HttpStatus.resolve(response.getStatus()).getReasonPhrase());
        return result;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}