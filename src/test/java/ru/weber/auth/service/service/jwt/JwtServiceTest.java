package ru.weber.auth.service.service.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.weber.auth.service.dto.TokenDto;
import ru.weber.auth.service.service.jwt.util.Base64Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class JwtServiceTest {

    private JwtKeyProvider jwtKeyProvider;

    private JwtService jwtService;

    // exp to Sun Jun 16 2024 12:58:56 GMT+0000
    private final String GOOD_TOKEN = "eyJhbGciOiJSUzI1NiJ9" +
            /*{"iss":"e8eb0af9-d902-41f9-9ae4-afd072d92681","exp":1718542736,"sub":"testLogin","firstName":"testFirstName","lastName":"testSurname","middleName":"testMiddleName"}*/
            ".eyJpc3MiOiJlOGViMGFmOS1kOTAyLTQxZjktOWFlNC1hZmQwNzJkOTI2ODEiLCJleHAiOjE3MTg1NDI3MzYsInN1YiI6InRlc3RMb2dpbiIsImZpcnN0TmFtZSI6InRlc3RGaXJzdE5hbWUiLCJsYXN0TmFtZSI6InRlc3RTdXJuYW1lIiwibWlkZGxlTmFtZSI6InRlc3RNaWRkbGVOYW1lIn0" +
            ".LM0IaJoFNF0UsUYWihdczEqq9MpU3K9qHS7z1INLCxGqNimPEWlH97E3Bgo18rdbE5K8wnKFOpIJ4GznbpvffySr_7UbTPvOrrtJpJjJ80oBUG0X6Z-hYTEL85pPtGT4umimAU14GZzXtr6iL0UjUaIyMyW8eB2lsxTn2-xrSeU2CFBU8qq4Bd0OXIjdDJ4nEuReZYpG2R8lN9EpLPrPO92W4iETIvehsVujdKEK0hTyOnotCw72V8ZMRJZYJOfcqURBVMWRR2ljJSCZJkvNcfUd2oNn0TCeiGbZ4isoNZPebpPjSvMojEVjwzeLafGr5czDXJv-E3ZsC75W1D_j-g";

    private final String FAKE_TOKEN = "eyJhbGciOiJSUzI1NiJ9" +
            /*exp was faked
            {"iss":"e8eb0af9-d902-41f9-9ae4-afd072d92681","exp":1818542736,"sub":"testLogin","firstName":"testFirstName","lastName":"testSurname","middleName":"testMiddleName"}*/
            ".eyJpc3MiOiJlOGViMGFmOS1kOTAyLTQxZjktOWFlNC1hZmQwNzJkOTI2ODEiLCJleHAiOjE4MTg1NDI3MzYsInN1YiI6InRlc3RMb2dpbiIsImZpcnN0TmFtZSI6InRlc3RGaXJzdE5hbWUiLCJsYXN0TmFtZSI6InRlc3RTdXJuYW1lIiwibWlkZGxlTmFtZSI6InRlc3RNaWRkbGVOYW1lIn0" +
            ".LM0IaJoFNF0UsUYWihdczEqq9MpU3K9qHS7z1INLCxGqNimPEWlH97E3Bgo18rdbE5K8wnKFOpIJ4GznbpvffySr_7UbTPvOrrtJpJjJ80oBUG0X6Z-hYTEL85pPtGT4umimAU14GZzXtr6iL0UjUaIyMyW8eB2lsxTn2-xrSeU2CFBU8qq4Bd0OXIjdDJ4nEuReZYpG2R8lN9EpLPrPO92W4iETIvehsVujdKEK0hTyOnotCw72V8ZMRJZYJOfcqURBVMWRR2ljJSCZJkvNcfUd2oNn0TCeiGbZ4isoNZPebpPjSvMojEVjwzeLafGr5czDXJv-E3ZsC75W1D_j-g";

    // exp to Wed Jun 15 2022 05:54:04 GMT+0000
    private final String EXPIRED_TOKEN = "eyJhbGciOiJSUzI1NiJ9" +
            ".eyJpc3MiOiJlOGViMGFmOS1kOTAyLTQxZjktOWFlNC1hZmQwNzJkOTI2ODEiLCJleHAiOjE2NTUyNzI0NDQsInN1YiI6Im92ZXJkdWVMb2dpbiIsImZpcnN0TmFtZSI6Im92ZXJkdWVGaXJzdE5hbWUiLCJsYXN0TmFtZSI6Im92ZXJkdWVTdXJuYW1lIiwibWlkZGxlTmFtZSI6Im92ZXJkdWVNaWRkbGVOYW1lIn0" +
            ".YdrRBbM1_-fZZtRlvAScjeE0E0KRDlybgWhrKuOt3dK8hyfLYKT7t4G8Pcjjj9lEMGX3E8Amh-hFciSu24o9Aa3OpkdVfCzlJ-geKt-4fSP4UJ-qWapo4ApvhS76fPOK15aMuZj2ZfFznTnj7XZ-hmx4hf3iP4ojybjQzXrbBa7z8OjDeMk7lnLj0n20XO7KmpFz36Rh6A-S9VxZ3Z5d7KofSwxU1mGaIvJxTxwhAGl2_xyy5yGtwf9lAaO1cuKBmDHC2OmfWFdlhRPVgu9N9EqUZq4YFS_Vq1gPDOyLQLNDX1uFifMNUrQYjuUJIahEKDvz5jnQqnJDc5AWbF4SWA";

    @BeforeEach
    void setUp() {
        jwtKeyProvider = new JwtKeyProvider(new Base64Util());
        jwtService = new JwtService(jwtKeyProvider);
        ReflectionTestUtils.setField(jwtService,
                "publicKey",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkuVYNDXkcx2VUssFAZub+n490wfsFH0n+IF3An8X3SvFFjTd88y8G+nVaC4wXvbG1E/XxYaDIQGfS2l7YxMEaBdRUIIqHNbr3f3+D276VR+8P9QKYHfeSZ4xlH4KjAPt3q4waV/Gr8oWwimk2eEGDq9zgrx5scIhtuzx32dLK4xuJnRjmmnvBeQMhwTZYvvAuzodU5r4mRpfppnmy0aEoVSLlMkBKC/mwdej7b6Ai5Af/N/k5XbkBl/7REtfsJ3Zc3TSuZcdtbjUppuSO+b8qpQGUO2o0OaAw9mb8IT94/zvNRbmzhR/R5BznlJtYNjnrxA4jRHXrr8ZSg8Hq8ZQ9QIDAQAB"
        );
    }

    @Test
    void getTokenDtoFromJwt_Ok() {
        TokenDto result = jwtService.verifyTokenAndCreateTokenDto(GOOD_TOKEN);
        assertEquals("testFirstName", result.getFirstName());
        assertEquals(GOOD_TOKEN, result.getSessionId());
    }

    @Test
    void getTokenDtoFromJwt_fakeToken() {
        assertThrows(SignatureException.class, () -> jwtService.verifyTokenAndCreateTokenDto(FAKE_TOKEN));
    }

    @Test
    void getTokenDtoFromJwt_ExpiredToken() {
        assertThrows(ExpiredJwtException.class, () -> jwtService.verifyTokenAndCreateTokenDto(EXPIRED_TOKEN));
    }
}