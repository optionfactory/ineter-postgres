package net.optionfactory.network.psql.inet;

import com.github.maltalex.ineter.base.IPv4Address;
import org.postgresql.util.PGBinaryObject;
import org.postgresql.util.PGobject;

import java.sql.SQLException;
import java.util.Arrays;

public class InetPgObject extends PGobject implements PGBinaryObject {

    private static final byte AF_INET = 2;
    private static final byte MASK32 = 32;
    private static final byte NOT_CIDR = 0;
    private static final byte IPV4_LEN = 4;

    private IPv4Address address;

    public IPv4Address getAddress() {
        return address;
    }

    public void setAddress(IPv4Address address) {
        this.address = address;
    }

    @Override
    public void setByteValue(byte[] bytes, int i) throws SQLException {
        if (bytes[i+3] != IPV4_LEN) {
            throw new IllegalArgumentException("Illegal size for ipv4, expected %d, found %d".formatted(IPV4_LEN, bytes[i+3]));
        }
        this.address = IPv4Address.of(Arrays.copyOfRange(bytes, i+4, i+8));
    }

    @Override
    public int lengthInBytes() {
        return 8;
    }

    @Override
    public void toBytes(byte[] bytes, int i) {
         bytes[i] = AF_INET;
        bytes[i+1] = MASK32;
        bytes[i+2] = NOT_CIDR;
        bytes[i+3] = IPV4_LEN;
        System.arraycopy(this.address.toBigEndianArray(), 0, bytes, i+4, 4);
    }

    @Override
    public void setValue(String value) throws SQLException {
        this.address = IPv4Address.of(value);
    }

    @Override
    public String getValue() {
        return address.toString();
    }
}
