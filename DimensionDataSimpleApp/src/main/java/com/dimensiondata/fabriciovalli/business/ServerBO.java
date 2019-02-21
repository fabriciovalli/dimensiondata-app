package com.dimensiondata.fabriciovalli.business;

import com.dimensiondata.fabriciovalli.model.Server;
import com.dimensiondata.fabriciovalli.repository.ServerRepository;
import com.dimensiondata.fabriciovalli.service.ServerService;
import com.dimensiondata.fabriciovalli.xml.ServerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ServerBO implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Override
    public void addServer(InputStream xml) throws JAXBException, IOException {
        ServerTO to = (ServerTO) marshaller.unmarshal(new StreamSource(xml));
        Server server = getServerFromTO(to);

        serverRepository.save(server);
    }

    public void addServer(Integer id, String name) {
        if(id == null) {
            //TODO id must not be null
        }
        if(name == null) {
            //TODO name must not be null
        }

        Server server = new Server(id, name);
        serverRepository.save(server);

    }

    public Server editServer(Integer id, String name) {
        if(id == null) {
            //TODO id must not be null
        }
        if(name == null || name.isBlank()) {
            //TODO name must not be null or blank
        }

        Server server = serverRepository.findById(id).get();
        if(server == null) {
            //TODO server not found
        }

        server.setName(name);
        return serverRepository.save(server);
    }

    public void removeServer(Integer id) {
        if(id == null) {
            //TODO id must not be null
        }

        serverRepository.deleteById(id);

    }

    public long getCount() {
        return serverRepository.count();
    }

    public Iterable<Server> listAll() {
        //TODO not sure if I should filter, but if I do, I would use Querydsl support.
        return serverRepository.findAll();
    }

    @Override
    public Page<Server> findAll(Pageable pageable) {
        //TODO not sure if I should filter, but if I do, I would use Querydsl support.
        return serverRepository.findAll(pageable);
    }

    private Server getServerFromTO(ServerTO to) {
        Server server = new Server();
        server.setId(to.getId());
        server.setName(to.getName());

        return server;
    }
}
