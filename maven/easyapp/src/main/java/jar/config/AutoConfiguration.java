package jar.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 说明
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2021/2/4 13:53
 */
@Configuration
@EnableConfigurationProperties(AppConfig.class)
public class AutoConfiguration {
}
