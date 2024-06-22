package tech.flycat.halo.plugin.redirection.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/6/22
 */
public class SettingUpdateEvent extends ApplicationEvent {
    public SettingUpdateEvent(Object source) {
        super(source);
    }

    public static SettingUpdateEvent onUpdate(Object source) {
        return new SettingUpdateEvent(source);
    }
}
