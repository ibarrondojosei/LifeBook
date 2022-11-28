package com.ncs503.Babybook.auth.utility;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.util.Collections;
import java.util.Map;

public class UsarNameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    @Override
    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String userName = convertedClaims.get("UserName") != null ?
                (String) convertedClaims.get("UserName") : "unknown";

        convertedClaims.put("UserName", userName);

        return convertedClaims;
    }
}
