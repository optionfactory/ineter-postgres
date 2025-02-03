package net.optionfactory.ineter.psql.ip;

import com.github.maltalex.ineter.base.IPv4Address;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import net.optionfactory.ineter.psql.HibernateOnPsqlTestConfig;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate5.SessionHolder;
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
            devices.save(Device.of("localhost", IPv4Address.of("1.1.1.1")));
        });

    }

    @Test
    public void canRead() {
        final var d = tx.execute(ts -> devices.findById("localhost").orElseThrow());
        Assert.assertEquals(IPv4Address.of("1.1.1.1"), d.ip);
    }
}
