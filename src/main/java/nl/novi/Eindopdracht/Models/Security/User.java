package nl.novi.Eindopdracht.Models.Security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
@Id
@Column(nullable = false,unique = true)
    private String username;
@Column(nullable = false,length = 255)
    private String password;
@Column(nullable = false)
    private boolean enabled = true;
@Column
    private String apiKey;
@Column
    private String email;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public Set<Authority> getAuthorities() { return authorities; }
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }



}
