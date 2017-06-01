package app.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album_roles")
public class AlbumRole implements Serializable {
    private Integer id;
    private User user;
    private Album album;
    private Role role;

    public AlbumRole() {
    }

    @Id
    @Column(name = "album_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s",this.getUser().getUsername(), this.getAlbum().getName(), this.getRole());
    }
}
