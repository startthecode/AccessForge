package org.samtar.cms.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.samtar.cms.common.exception.AuthException;
import org.samtar.cms.common.response.GenericResponse;
import org.samtar.cms.common.util.JwtUtils;
import org.samtar.cms.modules.accesscontrols.user.service.imps.UserDetailImps;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilterChain extends OncePerRequestFilter {

    private final List<String> unProtectedRoutes = List.of(
            "/api/v1/auth/**",
            "/api/testing/**");
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final ObjectMapper mapper;
    private final UserDetailImps userDetailImps;
    private final JwtUtils jwtUtils;

    public JwtFilterChain(JwtUtils jwtUtils, ObjectMapper mapper, UserDetailImps userDetailImps) {
        this.jwtUtils = jwtUtils;
        this.mapper = mapper;
        this.userDetailImps = userDetailImps;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getServletPath();
        return unProtectedRoutes.stream().anyMatch(p -> pathMatcher.match(p, requestPath));
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            if(header == null || !header.startsWith("Bearer")) {
                throw AuthException.missingAuthHeaders();
            }
            String accessToken = request.getHeader("Authorization").substring(7);

            if (accessToken.isEmpty()) {
                throw new RuntimeException("Access token required");
            }
            ;
            Claims tokenData = jwtUtils.decodeToken(accessToken, TokenTypes.Access);
            String username = tokenData.get("username", String.class);
            boolean isNotAuthenticated = SecurityContextHolder.getContext().getAuthentication() == null;
            if (!username.isEmpty() && isNotAuthenticated) {
                UserDetails user = userDetailImps.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            ;
            filterChain.doFilter(request, response);

        } catch (Exception err) {
            sendErrorResponse(response,err);
        }
    }

    private void sendErrorResponse(HttpServletResponse response,Exception exception) throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if(exception instanceof AuthException){
            GenericResponse<Null> res = new GenericResponse<>(null,exception.getMessage(),((AuthException) exception).getStatuscode(),true);
            mapper.writeValue(response.getWriter(),res);
        }else {
            GenericResponse<Null> res = new GenericResponse<>(null,"Something went wrong", HttpStatus.NOT_FOUND,true);
            mapper.writeValue(response.getWriter(),res);
        }
    }

}
