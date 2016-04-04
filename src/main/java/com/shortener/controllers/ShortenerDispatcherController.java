package com.shortener.controllers;

import com.shortener.entities.Shortener;
import com.shortener.infra.response.ErrorResponse;
import com.shortener.infra.response.ShortenerResponse;
import com.shortener.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by isvaldo on 03/04/16.
 */

@Controller
public class ShortenerDispatcherController {

    @Autowired
    private ShortenerRepository shortenerRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> index(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Shortener shortener = shortenerRepository.findById(id);

        if (shortener == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse(id,"002","SHORTENED URL NOT FOUND"));
        }

        response.sendRedirect(shortener.getTargetUrl());
        return ResponseEntity.ok().body(shortener);
    }
}
