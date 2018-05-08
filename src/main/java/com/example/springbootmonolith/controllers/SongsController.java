package com.example.springbootmonolith.controllers;

import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SongsController {

    //Ask spring to get dependency injection
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs")
    public Iterable<Song> findAllSongs() {
        return songRepository.findAll();
    }

    //Same name as long as add @PathVariable
    @GetMapping("/songs/{songId}")
    public Optional<Song> findSongById(@PathVariable Long songId) {
        return songRepository.findById(songId);
    }

    //Delete a song by ID
    @DeleteMapping("/songs/{songId}")
    public HttpStatus deleteSongById(@PathVariable Long songId) {
        songRepository.deleteById(songId);
        return HttpStatus.OK;
    }

    //Posting
    @PostMapping("/songs")
    public Song createNewSong(@RequestBody Song newSong) {
        return songRepository.save(newSong);
    }

    //Naive update
    @PatchMapping("/songs/{songId}")
    public Song updateSongById(@PathVariable Long songId, @RequestBody Song songRequest) {

        Song songFromDb = songRepository.findById(songId).get();

        songFromDb.setTitle(songRequest.getTitle());
        songFromDb.setArtist(songRequest.getArtist());
        songFromDb.setLength(songRequest.getLength());

        return songRepository.save(songFromDb);
    }

}