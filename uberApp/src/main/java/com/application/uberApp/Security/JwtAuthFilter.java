package com.application.uberApp.Security;

import com.application.uberApp.entities.User;
import com.application.uberApp.services.Impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
                final String requestTokenHeader=request.getHeader("Authorization");
                if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer")){
                    filterChain.doFilter(request,response);
                    return;
                }
                String token=requestTokenHeader.split("Bearer ")[1];
                Long userIdFromToken= jwtService.getUserIdFromToken(token);
                if(userIdFromToken!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    User user = userService.getUserById(userIdFromToken);
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
                filterChain.doFilter(request,response);

        }
        catch (Exception ex){
            handlerExceptionResolver.resolveException(request,response,null,ex);
        }

    }
}
