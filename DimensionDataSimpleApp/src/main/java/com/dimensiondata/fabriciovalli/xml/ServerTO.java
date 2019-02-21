package com.dimensiondata.fabriciovalli.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverTO", propOrder = {
        "id",
        "name"
})
@XmlRootElement(namespace = "http://www.opsource.net/simpleapp", name = "server" )
public class ServerTO {

    @XmlElement(namespace = "http://www.opsource.net/simpleapp", required = true)
    private int id;
    @XmlElement(namespace = "http://www.opsource.net/simpleapp", required = true)
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}