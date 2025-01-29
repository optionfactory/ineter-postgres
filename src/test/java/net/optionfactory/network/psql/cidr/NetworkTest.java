package net.optionfactory.network.psql.cidr;

import com.github.maltalex.ineter.range.IPv4Subnet;
import jakarta.inject.Inject;
import net.optionfactory.network.psql.HibernateOnPsqlTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateOnPsqlTestConfig.class)
public class NetworkTest {

    @Inject
    private NetworksRepository networks;
    @Inject
    private TransactionTemplate tx;

    @Before
    public void setup() {
        tx.executeWithoutResult((ts) -> {
            networks.save(Network.of("local", IPv4Subnet.of("1.1.0.0/16")));
        });
    }

    @Test
    public void canRead() {
        final var n = tx.execute((ts) -> networks.findById("local").orElseThrow());
        Assert.assertEquals(IPv4Subnet.of("1.1.0.0/16"), n.cidr);
    }
}
