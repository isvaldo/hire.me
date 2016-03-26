package com.shortener.services;

import com.shortener.entities.Shortener;
import com.shortener.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by isvaldo on 26/03/16.
 */

@Service
public class ShortenerService {

    @Autowired
    private ShortenerRepository shortenerRepository;

    public Shortener findById(String id){
        return shortenerRepository.findById(id);
    }

    public List<Shortener> findAll(){
        return  shortenerRepository.findAll();
    }
}
