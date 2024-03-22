package com.babelgroup.controllers;

import com.babelgroup.dtos.SinisterDto;
import com.babelgroup.services.sinister.SinisterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sinister")
public class SinisterController {
    private final SinisterService sinisterService;

    public SinisterController(SinisterService sinisterService) {
        this.sinisterService = sinisterService;
    }

    @PostMapping("/add")
    public String addSinister(@PathVariable SinisterDto sinisterDto) {
        return sinisterService.add(sinisterDto).id;
    }
}
