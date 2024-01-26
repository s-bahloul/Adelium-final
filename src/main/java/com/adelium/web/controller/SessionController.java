package com.adelium.web.controller;

import com.adelium.web.entity.SessionTraining;
import com.adelium.web.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping
    public ResponseEntity<SessionTraining> createSession(@RequestBody SessionTraining sessionTraining) {
        SessionTraining savedSessionTraining = sessionRepository.save(sessionTraining);
        return new ResponseEntity<>(savedSessionTraining, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionTraining> getSessionById(@PathVariable Long id) {
        SessionTraining sessionTraining = sessionRepository.getReferenceById(id);
        if (sessionTraining != null) {
            return new ResponseEntity<>(sessionTraining, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SessionTraining>> getAllSessions() {
        List<SessionTraining> sessionTrainings = sessionRepository.findAll();
        return new ResponseEntity<>(sessionTrainings, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

