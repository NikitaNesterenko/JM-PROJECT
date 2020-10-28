//package jm.stockx.rest_controller;
//
//import com.github.scribejava.core.model.OAuth2AccessToken;
//import jm.stockx.oAuth2.GoogleOAuth;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/auth")
//public class GoogleOAuthRestController {
//    private final GoogleOAuth googleService;
//
//    @Value("${jwt.cookieName}")
//    private String cookieName;
//
//    @Value("${google.successUrl}")
//    private String successUrl;
//
//    @Value("${google.authorizeUrl}")
//    private String authorizeUrl;
//
//    public GoogleOAuthRestController(GoogleOAuth googleService) {
//        this.googleService = googleService;
//    }
//
//    @GetMapping("url/google")
//    public String authUrl() {
//        return googleService.getAuthorizeUrl();
//    }
//
//    @GetMapping("/google")
//    public void googleAuth(@RequestParam String code, HttpServletResponse request) {
//        try {
//            OAuth2AccessToken oAuth2Token = googleService.getOAuthToken(code);
//            String jwtToken = googleService.getGoogleUserToken(oAuth2Token);
//
//            request.addCookie(new Cookie(cookieName, jwtToken));
//            request.sendRedirect(successUrl);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//            try {
//                request.sendRedirect(authorizeUrl);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//    }
//}
