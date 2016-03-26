package com.shortener.controllers;

import com.shortener.entities.Shortener;
import com.shortener.infra.Base62Converter;
import com.shortener.infra.ShortenerBuilder;
import com.shortener.repository.ShortenerRepository;
import com.shortener.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by isvaldo on 26/03/16.
 */

@RestController
public class ShortenerController {

    @Autowired
    private ShortenerRepository shortenerRepository;

    @RequestMapping("/api/create")
    public String create(@RequestParam String url,
                         @RequestParam(value="name", required = false) String name) {


        ShortenerBuilder shortener = null;
        try {
            shortener = new ShortenerBuilder<ShortenerRepository>(url, name,shortenerRepository);
            shortenerRepository.save(shortener.getShotener());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url+"\n"+name;




    }

    @RequestMapping("/api/info")
    public void top(){

    }

}
