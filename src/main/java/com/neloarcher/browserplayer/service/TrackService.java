package com.neloarcher.browserplayer.service;

import com.neloarcher.browserplayer.domain.Track;
import com.neloarcher.browserplayer.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrackService {
    void save(Track track);

    List<Track> findAll();

    boolean save(MultipartFile file, User user);

    Track findById(long id) throws Exception;
}