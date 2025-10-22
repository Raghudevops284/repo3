package jar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/1 17:05
 */
@ConfigurationProperties(prefix = "app.easyapp")
@Data
public class AppConfig {
}
