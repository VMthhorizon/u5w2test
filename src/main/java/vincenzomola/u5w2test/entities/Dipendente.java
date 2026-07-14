package vincenzomola.u5w2test.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vincenzomola.u5w2test.enums.Role;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Dipendenti")
public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id_dipendente")
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Column(name = "avatar_pic")
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    protected Dipendente() {
    }

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=John+Doe";
        this.role = Role.USER;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
