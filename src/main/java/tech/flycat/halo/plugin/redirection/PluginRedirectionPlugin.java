package tech.flycat.halo.plugin.redirection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import run.halo.app.extension.SchemeManager;
import run.halo.app.plugin.BasePlugin;
import run.halo.app.plugin.PluginContext;
import tech.flycat.halo.plugin.redirection.listener.SettingUpdateEvent;
import tech.flycat.halo.plugin.redirection.manager.RedirectionManager;

/**
 * <p>Plugin main class to manage the lifecycle of the plugin.</p>
 * <p>This class must be public and have a public constructor.</p>
 * <p>Only one main class extending {@link BasePlugin} is allowed per plugin.</p>
 *
 * @author guqing
 * @since 1.0.0
 */
@Slf4j
@Component
@EnableAsync
public class PluginRedirectionPlugin extends BasePlugin {
    private final SchemeManager schemeManager;
    private final ApplicationEventPublisher eventPublisher;

    public PluginRedirectionPlugin(PluginContext pluginContext, SchemeManager schemeManager, ApplicationEventPublisher eventPublisher) {
        super(pluginContext);
        this.schemeManager = schemeManager;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void start() {
        eventPublisher.publishEvent(SettingUpdateEvent.onUpdate(this));
        log.info("[plugin-redirection] - 重定向插件启动成功！");
    }

    @Override
    public void stop() {
        RedirectionManager.unloadSettings();
        log.info("[plugin-redirection] - 重定向插件停止！");
    }

    @Override
    public void delete() {
        this.stop();
        log.info("[plugin-redirection] - 重定向插件卸载！");
    }
}
