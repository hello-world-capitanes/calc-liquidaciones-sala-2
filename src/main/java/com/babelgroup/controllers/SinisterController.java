package com.babelgroup.controllers;

import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.services.sinister.SinisterService;
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
}
