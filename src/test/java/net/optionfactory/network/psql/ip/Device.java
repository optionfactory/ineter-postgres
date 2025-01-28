package net.optionfactory.network.psql.ip;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import net.optionfactory.network.psql.Inet4;

@Entity
public class Device {

    @Id
    public String hostname;
    public Inet4 ip;

    public static Device of(String hostname, Inet4 ip) {
        final Device d = new Device();
        d.hostname = hostname;
        d.ip = ip;
        return d;
    }

}
