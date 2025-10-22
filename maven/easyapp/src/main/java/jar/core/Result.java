package jar.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/2 13:28
 */
@Data
public class Result implements IResult {
    /**
     * 0-成功 非0-失败
     * -1 会话不存在
     */
    private int code = 0;
    private String message = "处理成功！";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
