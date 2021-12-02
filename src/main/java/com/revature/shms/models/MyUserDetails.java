package com.revature.shms.models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(){

    }

    //Override UserDetails to be our users
    public MyUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //change this
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //change this
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //change this
        return true;
    }

    @Override
    public boolean isEnabled() {
        //change this
        return true;
    }

}