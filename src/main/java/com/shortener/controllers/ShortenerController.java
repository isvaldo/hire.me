package com.shortener.controllers;

import com.shortener.entities.Shortener;
import com.shortener.infra.Base62Converter;
import com.shortener.infra.ErrorResponse;
import com.shortener.infra.ShortenerBuilder;
import com.shortener.repository.ShortenerRepository;
import com.shortener.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by isvaldo on 26/03/16.
 */

@RestController
public class ShortenerController {

    @Autowired
    private ShortenerBuilder shortenerBuilder;

    @RequestMapping(value = "/api/create", method = RequestMethod.GET)
    public ResponseEntity<?> create(@RequestParam String url,
                                    @RequestParam(value="customName", required = false) String customName) {


//        shortenerBuilder.
//                withTargetUrl(url).
//                withCustomName(customName).
//                save();
//
        return ResponseEntity.ok(new ErrorResponse("bemobi", "101", "Ja existe"));

    }


    @RequestMapping("/api/info")
    public void top(){

    }

}

