package net.optionfactory.ineter.psql.ip;

import com.github.maltalex.ineter.base.IPv4Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Device {

    @Id
    public String hostname;
    public IPv4Address ip;

    public static Device of(String hostname, IPv4Address ip) {
        final Device d = new Device();
        d.hostname = hostname;
        d.ip = ip;
        return d;
    }

}
