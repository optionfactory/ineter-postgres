package net.optionfactory.ineter.psql.subnet;

import com.github.maltalex.ineter.range.IPv4Subnet;
import jakarta.inject.Inject;
import net.optionfactory.ineter.psql.HibernateOnPsqlTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.support.TransactionTemplate;

@SpringJUnitConfig(HibernateOnPsqlTestConfig.class)
public class NetworkTest {

    @Inject
    private NetworksRepository networks;
    @Inject
    private TransactionTemplate tx;

    @BeforeEach
    public void setup() {
        tx.executeWithoutResult((ts) -> {
            networks.save(Network.of("local", IPv4Subnet.of("1.1.0.0/16")));
            networks.save(Network.of("local_null", null));
        });
    }

    @Test
    public void canRead() {
        final var n = tx.execute((ts) -> networks.findById("local").orElseThrow());
        Assertions.assertEquals(IPv4Subnet.of("1.1.0.0/16"), n.subnet);
    }

    @Test
    public void canReadNull() {
        final var n = tx.execute((ts) -> networks.findById("local_null").orElseThrow());
        Assertions.assertNull(n.subnet);
    }
}
