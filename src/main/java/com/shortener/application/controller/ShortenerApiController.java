package com.shortener.application.controller;

import com.shortener.domain.entities.Shortener;
import com.shortener.domain.repository.ShortenerRepository;
import com.shortener.domain.response.ErrorResponse;
import com.shortener.domain.services.ShortenerBuilder;
import com.shortener.domain.response.ShortenerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by isvaldo on 26/03/16.
 */

@RestController
public class ShortenerApiController {

    @Autowired
    private ShortenerBuilder shortenerBuilder;

    @Autowired
    private ShortenerRepository shortenerRepository;

    @RequestMapping(value = "/api/create", method = RequestMethod.GET)
    public ResponseEntity<?> create(@RequestParam String url,
                                    @RequestParam(value="customName", required = false) String customName) {

        final Long startTime = System.currentTimeMillis();
        try {
            shortenerBuilder.
                    withTargetUrl(url).
                    withCustomName(customName).
                    save();
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(shortenerBuilder.getId(), "101", "CUSTOM ALIAS ALREADY EXISTS"));
        }

        final Long endTime = System.currentTimeMillis();

        return ResponseEntity.ok(
                new ShortenerResponse(shortenerBuilder.getId(),"http://"+shortenerBuilder.getId(),
                        new HashMap<String, String>() {{
                        put("time", String.valueOf(endTime - startTime)+"ms");
                        }}
                ));

    }


    @RequestMapping("/api/info")
    @ResponseBody
    public List<Shortener> info(){

        List<Shortener> shorteners = shortenerRepository.findAll();
        Collections.sort(shorteners, (Shortener s1, Shortener s2) -> s2.views.compareTo(s1.views));
        return shorteners;
    }

}

