package bd.ohedulalam.jwtsecurityspring.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String userName;
    private int userId;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    public CustomUserDetails(String userName, int userId, String token, List<GrantedAuthority> grantedAuthorities) {
        this.userName=userName;
        this.userId = userId;
        this.token = token;
        this.authorities = grantedAuthorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }


    public String getToken() {
        return token;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
