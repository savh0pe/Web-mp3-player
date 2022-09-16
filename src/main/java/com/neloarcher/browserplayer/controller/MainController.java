package com.neloarcher.browserplayer.controller;

import com.neloarcher.browserplayer.domain.Track;
import com.neloarcher.browserplayer.domain.User;
import com.neloarcher.browserplayer.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private TrackServiceImpl trackService;

    @GetMapping("/")
    public String mainPage(
            @AuthenticationPrincipal User user,
            Model model) {
        for (Track tr : user.getTracks())
            System.out.print(tr);
        model.addAttribute("tracks", user.getTracks());
        return "main";
    }

    @PostMapping("/addMusic")
    public String addMusicToServer(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            Model model) throws Exception {
        boolean saved = false;
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            saved = trackService.save(file, user);
        }

        if (!saved)
            model.addAttribute("message", "No file");

        return "redirect:/";
    }

    @GetMapping("/allMusic")
    public String allMusic(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        model.addAttribute("tracks", trackService.findAll());
        return "allMusic";
    }
}