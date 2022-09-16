package com.neloarcher.browserplayer.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String album;
    private String artist;
    private String length;
    private String year;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_music",
            joinColumns = @JoinColumn(name = "usr_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "track_id",referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<>();

    public Track() { }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, album, artist);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track) {
            Track track = (Track) obj;
            return this.id == track.id;
        }
        return false;
    }
}