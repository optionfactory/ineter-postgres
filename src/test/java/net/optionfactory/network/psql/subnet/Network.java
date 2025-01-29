package net.optionfactory.network.psql.subnet;

import com.github.maltalex.ineter.range.IPv4Subnet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Network {

    @Id
    public String name;

    public IPv4Subnet subnet;

    public static Network of(String name, IPv4Subnet subnet) {
        final Network d = new Network();
        d.name = name;
        d.subnet = subnet;
        return d;
    }

}
