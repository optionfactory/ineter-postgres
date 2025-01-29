package net.optionfactory.network.psql.cidr;

import com.github.maltalex.ineter.range.IPv4Subnet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Network {

    @Id
    public String name;

    public IPv4Subnet cidr;

    public static Network of(String name, IPv4Subnet cidr) {
        final Network d = new Network();
        d.name = name;
        d.cidr = cidr;
        return d;
    }

}
