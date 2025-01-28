package net.optionfactory.network.psql.cidr;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import net.optionfactory.network.psql.Cidr4;

@Entity
public class Network {

    @Id
    public String name;

    public Cidr4 cidr;

    public static Network of(String name, Cidr4 cidr) {
        final Network d = new Network();
        d.name = name;
        d.cidr = cidr;
        return d;
    }

}
