package tech.flycat.halo.plugin.redirection;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import run.halo.app.security.AdditionalWebFilter;
import tech.flycat.halo.plugin.redirection.config.BasicSetting;
import tech.flycat.halo.plugin.redirection.listener.SettingUpdateEvent;
import tech.flycat.halo.plugin.redirection.manager.RedirectionManager;
import tech.flycat.halo.plugin.redirection.util.UrlUtil;

/**
 * @author <a href="mailto:zengbin@hltn.com">zengbin</a>
 * @since 2024/5/5
 */
@Component
@RequiredArgsConstructor
public class RedirectionWebFilter implements AdditionalWebFilter {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var request = exchange.getRequest();
        String path = UrlUtil.trimEndSlash(request.getURI().getPath());

        if (!path.startsWith("/api")) {
            Optional<BasicSetting.RedirectionConfig> redirectionConfig = RedirectionManager.get(path);
            if (redirectionConfig.isPresent()) {
                BasicSetting.RedirectionConfig config = redirectionConfig.get();
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatusCode.valueOf(config.getRedirectType()));
                response.getHeaders().set("Location", config.getTargetLinkPath());
                return Mono.empty();
            }
        }

        return chain.filter(exchange).doOnSuccess(e -> {
            if (RedirectionManager.checkChangePluginSettingRequest(request)) {
                eventPublisher.publishEvent(SettingUpdateEvent.onUpdate(this));
            }
        });

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
