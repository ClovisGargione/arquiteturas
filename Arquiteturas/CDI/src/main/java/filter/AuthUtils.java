/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import core.Token;
import java.text.ParseException;
import org.joda.time.DateTime;

/**
 *
 * @author clovis
 */
public class AuthUtils {
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = "aliceinwonderland";
    public static final String AUTH_HEADER_KEY = "Authorization";

    public static String getSubject(String authHeader) throws JOSEException, ParseException {
        return decodeToken(authHeader).getSubject();
    }

    public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException, java.text.ParseException {
        SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
        if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
            return signedJWT.getJWTClaimsSet();
        } else {
            throw new JOSEException("Signature verification failed");
        }
    }


    public static String getSerializedToken(String authHeader) {
        return authHeader.split(" ")[1];
    }

    public static Token createToken(String remoteHost, Long id, String nome,String login) throws JOSEException {
        JWTClaimsSet claim = new JWTClaimsSet();
        claim.setSubject(Long.toString(id));
        claim.setIssuer(remoteHost);
        claim.setIssueTime(DateTime.now().toDate());
        claim.setExpirationTime(DateTime.now().plusDays(14).toDate());

        claim.setCustomClaim("nome", nome);
        claim.setCustomClaim("login", login);

        JWSSigner signer = new MACSigner(TOKEN_SECRET);
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        jwt.sign(signer);

        return new Token(jwt.serialize());
    }


    public static boolean tokenExpirado(JWTClaimsSet claimSet){

        if(new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now()))
            return true;

        return false;
    }

    public static boolean palavraSecretaValida(String token){
        try {
            JWSVerifier verifier = new MACVerifier(TOKEN_SECRET);
            SignedJWT jwt = SignedJWT.parse(getSerializedToken(token));
            return jwt.verify(verifier);
        } catch (ParseException pe) {
            return false;
        } catch (JOSEException je) {
            return false;
        }
    }
}
