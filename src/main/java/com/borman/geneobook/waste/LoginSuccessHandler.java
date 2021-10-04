//package com.borman.geneobook.service;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.FlashMap;
//import org.springframework.web.servlet.support.SessionFlashMapManager;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//
//        SessionFlashMapManager sessionFlashMapManager = new SessionFlashMapManager();
//        FlashMap flashMap = new FlashMap();
//        flashMap.put("mensaje", "alerta(\"Bienvenido " + authentication.getName() + "!\");");
//        sessionFlashMapManager.saveOutputFlashMap(flashMap, request, response);
//
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//
//}