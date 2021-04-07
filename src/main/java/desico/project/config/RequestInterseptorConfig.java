package desico.project.config;

import desico.project.web.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterseptorConfig implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;

    @Autowired
    public RequestInterseptorConfig(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }
}
