package net.optionfactory.ineter.psql.ip;

import com.github.maltalex.ineter.base.IPv4Address;
import jakarta.inject.Inject;
import net.optionfactory.ineter.psql.HibernateOnPsqlTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.support.TransactionTemplate;

@SpringJUnitConfig(HibernateOnPsqlTestConfig.class)
public class DeviceTest {

    @Inject
    private DevicesRepository devices;
    @Inject
    private TransactionTemplate tx;

    @BeforeEach
    public void setup() {
        tx.executeWithoutResult(ts -> {
            devices.save(Device.of("localhost", IPv4Address.of("1.1.1.1")));
            devices.save(Device.of("localhost_null", null));
        });

    }

    @Test
    public void canRead() {
        final var d = tx.execute(ts -> devices.findById("localhost").orElseThrow());
        Assertions.assertEquals(IPv4Address.of("1.1.1.1"), d.ip);
    }

    @Test
    public void canReadNull() {
        final var d = tx.execute(ts -> devices.findById("localhost_null").orElseThrow());
        Assertions.assertNull(d.ip);
    }
}
