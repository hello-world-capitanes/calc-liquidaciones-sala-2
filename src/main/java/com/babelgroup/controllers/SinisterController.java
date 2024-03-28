package com.babelgroup.controllers;

import com.babelgroup.dtos.DamageDto;
import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.services.sinister.SinisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sinister")
public class SinisterController {
    private final SinisterService sinisterService;

    public SinisterController(SinisterService sinisterService) {
        this.sinisterService = sinisterService;
    }

    @PostMapping("/")
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

    @GetMapping("/{id}/damages")
    public List<DamageDto> getSinisterDamages(@PathVariable String id) {
        return sinisterService.getSinisterDamages(id);
    }

    @PostMapping("/{id}/damages")
    public ResponseEntity<SinisterDto> addDamageToSinister(@PathVariable String id, @RequestBody DamageDto damageDto) {
        SinisterDto updatedSinisterDto = sinisterService.addDamageToSinister(id, damageDto);
        return ResponseEntity.ok(updatedSinisterDto);
    }
}
