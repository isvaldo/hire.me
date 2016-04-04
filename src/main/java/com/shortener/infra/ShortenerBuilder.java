package com.shortener.infra;

import com.shortener.entities.Shortener;
import com.shortener.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.shortener.entities.Shortener.getNextId;

/**
 * Created by isvaldo on 26/03/16.
 */


@Service
public class ShortenerBuilder {

    @Autowired
    private ShortenerRepository shortenerRepository;
    public String targetUrl;
    public String id;
    public String customName;


    public void save(){
        if (customName == null) {
            // gerar uma id randomica

            String id = getHashName();
            while(shortenerRepository.findById(this.id) == null)
                id = getHashName();

            shortenerRepository.save(new Shortener(id, this.targetUrl));

        }else {

            if (shortenerRepository.findById(customName) == null){

                shortenerRepository.save(new Shortener(customName, this.targetUrl));

            }else {
                throw new IllegalArgumentException("The custom name already exists");
            }
        }

    }

    private String getHashName(){
        this.id = getNextId();
       return  Base62Converter.converter(Integer.parseInt(this.id));
    }


    public ShortenerBuilder withTargetUrl(String targetUrl){
        this.targetUrl = targetUrl;
        return this;
    }

    public ShortenerBuilder withCustomName(String customName) {
        this.customName = customName;
        return this;
    }

    public ShortenerBuilder withId(String id) {
        this.id = id;
        return this;
    }

}
