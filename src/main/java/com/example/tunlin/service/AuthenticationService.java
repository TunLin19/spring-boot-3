package com.example.tunlin.service;

import com.example.tunlin.dto.request.AuthenticationRequest;
import com.example.tunlin.dto.request.IntrospectRequest;
import com.example.tunlin.dto.response.AuthenticationResponse;
import com.example.tunlin.dto.response.IntrospectResponse;
import com.example.tunlin.entity.User;
import com.example.tunlin.exception.AppException;
import com.example.tunlin.exception.ErrorCode;
import com.example.tunlin.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    private String SECRET_KEY;

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest){
        User user = userRepository.findByUserName(authenticationRequest.getUserName())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXIT));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(authenticationRequest.getPassword());
        System.out.println(user.getPassword());
        boolean authentication = passwordEncoder.matches(authenticationRequest.getPassword(),user.getPassword());
        System.out.println(authentication);
        if (!authentication)
            throw new AppException(ErrorCode.AUTHENTICATION);
        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .auth(true)
                .token(token)
                .build();
    }
    private String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("tunlin.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope",buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can't create token");
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }

}
