package net.optionfactory.network.psql.cidr;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworksRepository extends JpaRepository<Network, String> {

}
