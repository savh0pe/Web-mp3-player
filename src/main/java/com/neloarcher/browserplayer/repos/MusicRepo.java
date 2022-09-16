package com.neloarcher.browserplayer.repos;

import com.neloarcher.browserplayer.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepo extends JpaRepository<Track,Long> {
    Track findByNameAndArtistAndAlbum(String name, String artist, String album);
}