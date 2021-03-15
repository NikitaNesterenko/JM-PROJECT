package jm.stockx.rest_controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Service
public class RestCustomHeaderVerificator implements HandlerInterceptor {

    public boolean getVerificationValue(HttpServletRequest request){
        String test = Base64.getEncoder().
                encodeToString(request.getRequestURI().getBytes(StandardCharsets.UTF_8));
        String headersEncodingString = request.getHeader("Token");

        return test.equals(headersEncodingString);
    }

//    protected String getRequestSessionId() {
//        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
//        return request.getHeader("Token");
//    }

}
