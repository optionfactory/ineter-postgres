package net.optionfactory.network.psql.cidr;

import com.github.maltalex.ineter.base.IPv4Address;
import com.github.maltalex.ineter.range.IPv4Subnet;
import org.postgresql.util.PGBinaryObject;
import org.postgresql.util.PGobject;

import java.sql.SQLException;
import java.util.Arrays;

public class CidrPgObject extends PGobject implements PGBinaryObject {

    private static final byte AF_INET = 2;
    private static final byte CIDR = 1;
    private static final byte IPV4_LEN = 4;

    private IPv4Subnet subnet;

    public IPv4Subnet getSubnet() {
        return subnet;
    }

    public void setSubnet(IPv4Subnet subnet) {
        this.subnet = subnet;
    }

    @Override
    public void setByteValue(byte[] bytes, int i) throws SQLException {
        if (bytes[i+3] != IPV4_LEN) {
            throw new IllegalArgumentException("Illegal size for ipv4, expected %d, found %d".formatted(IPV4_LEN, bytes[i+3]));
        }
        this.subnet = IPv4Subnet.of(IPv4Address.of(Arrays.copyOfRange(bytes, i+4, i+8)), bytes[i+1]);
    }

    @Override
    public int lengthInBytes() {
        return 8;
    }

    @Override
    public void toBytes(byte[] bytes, int i) {
        bytes[i] = AF_INET;
        bytes[i+1] = (byte) this.subnet.getNetworkBitCount();
        bytes[i+2] = CIDR;
        bytes[i+3] = IPV4_LEN;
        System.arraycopy(this.subnet.getNetworkAddress().toBigEndianArray(), 0, bytes, i+4, 4);
    }

    @Override
    public void setValue(String value) throws SQLException {
        this.subnet = IPv4Subnet.of(value);
    }

    @Override
    public String getValue() {
        return subnet.toString();
    }
}