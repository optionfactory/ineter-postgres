package net.optionfactory.network.psql.ip;

import jakarta.inject.Inject;
import net.optionfactory.network.psql.HibernateOnPsqlTestConfig;
import net.optionfactory.network.psql.Inet4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateOnPsqlTestConfig.class)
public class DeviceTest {

    @Inject
    private DevicesRepository devices;
    @Inject
    private TransactionTemplate tx;

    @Before
    public void setup() {
        tx.executeWithoutResult(ts -> {
            devices.save(Device.of("localhost", Inet4.parse("1.1.1.1")));
        });

    }

    @Test
    public void canRead() {
        final var d = tx.execute(ts -> devices.findById("localhost").orElseThrow());
        Assert.assertEquals(Inet4.parse("1.1.1.1"), d.ip);
    }
}
