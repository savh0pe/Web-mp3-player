package com.neloarcher.browserplayer.controller;

import com.neloarcher.browserplayer.domain.Track;
import com.neloarcher.browserplayer.domain.User;
import com.neloarcher.browserplayer.repos.UserRepo;
import com.neloarcher.browserplayer.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonController {
    @Autowired
    private TrackService trackService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/addMusicToProfile")
    public String addMusicToProfile(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, Long> request
    ) {
        try {
            long id = request.get("id");
            Track track = trackService.findById(id);
            user.addTrack(track);
            trackService.save(track);
        } catch (Exception ex) {return ex.getMessage();}
        return "OK";
    }

    @PostMapping("/removeMusicFromProfile")
    public String removeMusicFromProfile(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, Long> request
    ) {
        try {
            long id = request.get("id");
            Track track = trackService.findById(id);
            user.removeTrack(track);
            trackService.save(track);
        } catch (Exception ex) {return ex.getMessage();}
        return "OK";
    }
}