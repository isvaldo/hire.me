package com.shortener.infra;

import com.shortener.entities.Shortener;
import com.shortener.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by isvaldo on 26/03/16.
 */

@Service
public class ShortenerBuilder {

    @Autowired
    private ShortenerRepository shortenerRepository;

    public String codeName;
    public String url;
    public String id;

    public ShortenerBuilder() throws Exception {


        if ( codeName != null) {
            //custom
            if (shortenerRepository.isCodenameInDb(codeName))
                throw new Exception("CustomName exist");

            this.codeName = codeName;
            this.id = Shortener.generateNextId();

        } else {
            // hash
            String hashName = getHashName();
            while(shortenerRepository.isCodenameInDb(hashName))
                hashName = getHashName();

            this.codeName = hashName;
        }

        this.url = url;
    }

    private String getHashName(){
        this.id = Shortener.generateNextId();
       return  Base62Converter.converter(Integer.parseInt(this.id));
    }

    public Shortener getShotener(){
        return new Shortener(this.id, this.url, this.codeName);
    }
}
