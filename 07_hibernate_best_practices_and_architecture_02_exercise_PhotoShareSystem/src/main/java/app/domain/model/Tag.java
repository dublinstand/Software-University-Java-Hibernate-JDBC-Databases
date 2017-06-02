package app.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {
    private Integer id;
    private String name;
    private Set<Album> albums;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    @app.validation.Tag
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        Tag other = (Tag) obj;
        return this.name.equals(other.getName());
    }
}
