/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author clovis
 */
public class AuthFilter implements Filter {

    private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header";
    private static final String EXPIRE_ERROR_MSG = "Token has expired";
    private static final String JWT_ERROR_MSG = "Unable to parse JWT";
    private static final String JWT_INVALID_MSG = "Invalid JWT token";
    private static final String INVALIDTOKEN_ERROR_MSG = "Token inválido";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpRequest.getHeader(AuthUtils.AUTH_HEADER_KEY);

        if (StringUtils.isBlank(authHeader)
                || authHeader.split(" ").length != 2) {

            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    AUTH_ERROR_MSG);
        } else {
            JWTClaimsSet claimSet = null;
            try {
                claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
            } catch (ParseException e) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        JWT_ERROR_MSG);
            } catch (JOSEException e) {
                // TODO Auto-generated catch block

            }


            // ensure that the token is not expired
            // verificando se o token expirou
            if (AuthUtils.tokenExpirado(claimSet)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, EXPIRE_ERROR_MSG);
            }

            if(!AuthUtils.palavraSecretaValida(authHeader)) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, INVALIDTOKEN_ERROR_MSG);
            }

            if(claimSet.getCustomClaim("nome") == null){
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, INVALIDTOKEN_ERROR_MSG);
            }

            if(claimSet.getCustomClaim("login") == null){
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, INVALIDTOKEN_ERROR_MSG);
            }

            filterChain.doFilter(servletRequest, servletResponse);

        }
    }

    @Override
    public void destroy() {

    }

}
