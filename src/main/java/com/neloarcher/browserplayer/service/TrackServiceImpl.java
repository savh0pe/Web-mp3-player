package com.neloarcher.browserplayer.service;

import com.neloarcher.browserplayer.domain.Track;
import com.neloarcher.browserplayer.domain.User;
import com.neloarcher.browserplayer.repos.MusicRepo;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private MusicRepo musicRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public boolean save(MultipartFile file, User user) {
        File uploadDir = new File(Paths.get(uploadPath).toAbsolutePath().toString()
                .replace("~", ""));
        String name, artist, album, length, year;

        try {
            if (!uploadDir.exists())
                uploadDir.mkdirs();
            try (InputStream in = file.getInputStream()) {
                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parsCtx = new ParseContext();
                parser.parse(in, handler, metadata, parsCtx);
                String[] metadataNames = metadata.names();
                name = metadata.get("title");
                artist = metadata.get("xmpDM:artist");
                album = metadata.get("xmpDM:album");
                length = metadata.get("xmpDM:duration");
                year = metadata.get("xmpDM:releaseDate");
                Path path = Paths.get(uploadDir.toString() + "/"
                        + name + " (" + album + ") - " + artist + ".mp3");

                if (musicRepo.findByNameAndArtistAndAlbum(name, artist, album) == null) {
                    Track track = new Track();
                    track.setName(name);
                    track.setAlbum(album);
                    track.setArtist(artist);
                    track.setYear(year);
                    track.setLength(length);
                    file.transferTo(path);
                    musicRepo.save(track);
                    user.addTrack(track);
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    @Override
    public void save(Track track){
        musicRepo.save(track);
    }

    @Override
    public List<Track> findAll() {return musicRepo.findAll();}

    @Override
    public Track findById(long id) throws Exception {
        Optional<Track> track = musicRepo.findById(id);
        if (!track.isPresent())
            throw new Exception("No such track");
        return track.get();
    }
}
