package jm.stockx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomRequestCache extends HttpSessionRequestCache {

    private final SecurityUtils securityUtils;

    @Autowired
    public CustomRequestCache(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!securityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }

}