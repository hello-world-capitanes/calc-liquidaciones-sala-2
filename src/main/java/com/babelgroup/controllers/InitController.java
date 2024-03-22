package com.babelgroup.controllers;

import com.babelgroup.repositories.BaseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    private final BaseData baseData;
    private boolean initialized = false;

    public InitController(BaseData baseData) {
        this.baseData = baseData;
    }

    @RequestMapping("/init")
    public void init() {
        if (!initialized) {
            initialized = true;
            baseData.init();
        }
    }
}
