package com.vulenhtho.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    private String determineTargetUrl(Authentication authentication){
        String url ="";
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
            grantedAuthorities.add(grantedAuthority);
        }
        if (isAdmin(grantedAuthorities)){
            url="/admin/home";
        }else if (isUser(grantedAuthorities)){
            url="/web";
        }
        return url;
    }

    private boolean isUser(Set<GrantedAuthority> grantedAuthorities){
        return grantedAuthorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
    }

    private boolean isAdmin(Set<GrantedAuthority> grantedAuthorities){
        return grantedAuthorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
}
