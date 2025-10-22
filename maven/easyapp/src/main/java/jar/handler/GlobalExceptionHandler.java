package jar.handler;

import jar.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/2 13:52
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result globalException(HttpServletRequest request, Exception ex) {
        log.error("{}, URL:{}", ex.getMessage(), request.getRequestURI());
        Result result = new Result();
        result.setCode(-2);
        result.setMessage(ex.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(HttpSessionRequiredException.class)
    public Result sessionException(HttpServletRequest request, HttpSessionRequiredException ex) {
        log.warn("{}, URL:{}", ex.getMessage(), request.getRequestURI());
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(ex.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public Result sessionException(HttpServletRequest request, SQLException ex) {
        log.warn("{}, URL:{}", ex.getMessage(), request.getRequestURI());
        Result result = new Result();
        result.setCode(ex.getErrorCode());
        result.setMessage("数据库异常！");
        return result;
    }
}