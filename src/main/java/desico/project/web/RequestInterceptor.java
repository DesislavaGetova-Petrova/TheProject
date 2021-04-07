package desico.project.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        FileWriter interseptorWriter = new FileWriter("src/main/java/desico/project/log/RequestTracker.log", true);
        interseptorWriter.write(String.format("User made request on %s with status code: {%d}%n", request.getRequestURI(), response.getStatus()));
        interseptorWriter.close();
        return true;
    }
}

