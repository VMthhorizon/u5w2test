package vincenzomola.u5w2test.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        filterChain.doFilter(request, response);
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnavailableException("Inserire il token nell'header");

        String accessToken = authHeader.replace("Bearer ", "");
        

    }
}
