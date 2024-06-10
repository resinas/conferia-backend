package org.example.entities;
import lombok.Data;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String company;
    private String country;
    private Boolean sharingchoice = false;
    private String avatar_path;
    @OneToMany(mappedBy = "owner")
    private List<GalleryImage> galleryImages;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "likedBy")
    private List<GalleryImage> likes;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "session_likes",
            joinColumns = @JoinColumn(name = "sessionheader_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<SessionHeader> likedBy;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    public User(String country, String company, String email, String firstname, String lastname, Boolean sharingchoice) {
        this.country = country;
        this.company = company;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sharingchoice = sharingchoice;
    }

    @Override
    public String getUsername() {
        return email;
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
}
