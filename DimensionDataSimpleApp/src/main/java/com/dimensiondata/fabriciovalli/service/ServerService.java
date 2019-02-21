package com.dimensiondata.fabriciovalli.service;

import com.dimensiondata.fabriciovalli.model.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

public interface ServerService {
    void addServer(InputStream xml) throws JAXBException, IOException;
    void addServer(Integer id, String name);
    Server editServer(Integer id, String name);
    void removeServer(Integer id);
    long getCount();
    Iterable<Server> listAll();
    Page<Server> findAll(Pageable pageable);

}
