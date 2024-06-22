package tech.flycat.halo.plugin.redirection.listener;

import io.github.resilience4j.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.SettingFetcher;
import tech.flycat.halo.plugin.redirection.config.BasicSetting;
import tech.flycat.halo.plugin.redirection.manager.RedirectionManager;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/6/22
 */
@Slf4j
@Component
public class SettingsUpdateListener implements ApplicationListener<SettingUpdateEvent> {
    @Autowired
    private SettingFetcher settingFetcher;

    @Async
    @Override
    public void onApplicationEvent(@NonNull SettingUpdateEvent event) {
        log.info("[plugin-redirection] - 收到配置变更事件");
        settingFetcher.fetch("basic", BasicSetting.class).ifPresent(basicSetting -> {
            log.info("[plugin-redirection] - 加载重定向配置：" + basicSetting);
            RedirectionManager.reloadSettings(basicSetting);
        });
    }
}
