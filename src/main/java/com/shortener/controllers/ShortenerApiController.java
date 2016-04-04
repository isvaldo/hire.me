package com.shortener.controllers;

import com.shortener.infra.response.ErrorResponse;
import com.shortener.infra.ShortenerBuilder;
import com.shortener.infra.response.ShortenerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by isvaldo on 26/03/16.
 */

@RestController
public class ShortenerApiController {

    @Autowired
    private ShortenerBuilder shortenerBuilder;

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
    public void top(){

    }

}

