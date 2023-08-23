package mindswap.academy.springorders.auth.model;

import jakarta.persistence.*;
import mindswap.academy.springorders.user.model.User;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public static class TokenBuilder {
        private final Token token;

        public TokenBuilder user(User user) {
            token.setUser(user);
            return this;
        }

        public TokenBuilder token(String jwt) {
            token.setToken(jwt);
            return this;
        }

        public TokenBuilder tokenType(TokenType tokenType) {
            token.setTokenType(tokenType);
            return this;
        }

        public TokenBuilder expired(boolean expired) {
            token.setExpired(expired);
            return this;
        }

        public TokenBuilder revoked(boolean revoked) {
            token.setRevoked(revoked);
            return this;
        }

        private TokenBuilder() {
            token = new Token();
        }

        public Token build() {
            return token;
        }
    }
}
