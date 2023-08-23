package mindswap.academy.springorders.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
        private final AuthenticationResponse authenticationResponse;

        public AuthenticationResponseBuilder accessToken(String token) {
            authenticationResponse.setAccessToken(token);
            return this;
        }

        public AuthenticationResponseBuilder refreshToken(String refreshToken) {
            authenticationResponse.setRefreshToken(refreshToken);
            return this;
        }

        private AuthenticationResponseBuilder() {
            authenticationResponse = new AuthenticationResponse();
        }

        public AuthenticationResponse build() {
            return authenticationResponse;
        }
    }
}
