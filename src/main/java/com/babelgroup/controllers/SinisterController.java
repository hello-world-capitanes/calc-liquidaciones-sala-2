package com.babelgroup.controllers;

import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.services.sinister.SinisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sinister")
public class SinisterController {
    private final SinisterService sinisterService;

    public SinisterController(SinisterService sinisterService) {
        this.sinisterService = sinisterService;
    }

    @PostMapping("/add")
    public String addSinister(@RequestBody SinisterDto sinisterDto) {
        return sinisterService.add(sinisterDto).id;
    }

    @GetMapping("/{id}")
    public SinisterDto getSinister(@PathVariable String id) {
        return sinisterService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSinister(@PathVariable String id, @RequestBody SinisterDto sinisterDto) {
        SinisterDto updatedSinister = sinisterService.update(id, sinisterDto);
        return ResponseEntity.ok("Sinister with ID " + id + " updated successfully.");
    }
}
