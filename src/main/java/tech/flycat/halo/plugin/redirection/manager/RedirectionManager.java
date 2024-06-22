package tech.flycat.halo.plugin.redirection.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import tech.flycat.halo.plugin.redirection.config.BasicSetting;
import tech.flycat.halo.plugin.redirection.util.UrlUtil;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/6/22
 */
public class RedirectionManager {
    private static final String UPDATE_SETTING_PATH =
        "/apis/api.console.halo.run/v1alpha1/plugins/plugin-redirection/config";

    private static Map<String, BasicSetting.RedirectionConfig> redirectionConfigMap;

    public static void reloadSettings(BasicSetting basicSetting) {
        unloadSettings();
        if (basicSetting == null) {
            return;
        }
        Boolean enableRedirection = basicSetting.getEnableRedirection();
        List<BasicSetting.RedirectionConfig> redirectionConfig = basicSetting.getRedirectionConfig();

        if (enableRedirection == null || !enableRedirection || redirectionConfig == null) {
            return;
        }

        synchronized (RedirectionManager.class) {
            redirectionConfigMap = new HashMap<>();
            redirectionConfig.forEach(config ->
                redirectionConfigMap.put(UrlUtil.trimEndSlash(config.getOriginLinkPath()), config));
        }
    }

    public static void unloadSettings() {
        synchronized (RedirectionManager.class) {
            redirectionConfigMap = null;
        }
    }

    public static Optional<BasicSetting.RedirectionConfig> get(String urlPath) {
        if (!isEnabled()) {
            return Optional.empty();
        }
        return Optional.ofNullable(redirectionConfigMap.get(urlPath));
    }

    public static boolean isEnabled() {
        return redirectionConfigMap != null;
    }

    public static boolean checkChangePluginSettingRequest(ServerHttpRequest request) {
        return UPDATE_SETTING_PATH.equals(request.getURI().getPath())
            && !HttpMethod.GET.equals(request.getMethod());
    }
}
