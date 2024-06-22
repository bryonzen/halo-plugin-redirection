package tech.flycat.halo.plugin.redirection.config;

import java.util.List;
import lombok.Data;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/6/22
 */
@Data
public class BasicSetting {
    private Boolean enableRedirection;
    private List<RedirectionConfig> redirectionConfig;

    @Data
    public static class RedirectionConfig {
        private String originLinkPath;
        private String targetLinkPath;
        private Integer redirectType;
    }
}
