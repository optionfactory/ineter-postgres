package net.optionfactory.network.psql.binary;

import com.github.maltalex.ineter.base.IPv4Address;
import org.postgresql.util.PGBinaryObject;
import org.postgresql.util.PGobject;

import java.sql.SQLException;

public class InetPgObject extends PGobject implements PGBinaryObject {

    private static final byte IPV4_ID = 2;
    private static final byte MASK32 = 32;
    private static final byte NOT_CIDR = 0;
    private static final byte IPV4_LEN = 4;

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void setByteValue(byte[] bytes, int i) throws SQLException {
        this.bytes = new byte[4];
        if (bytes[i+3] != IPV4_LEN) {
            throw new IllegalArgumentException("Illegal size for ipv4, expected %d, found %d".formatted(IPV4_LEN, bytes[i+3]));
        }
        System.arraycopy(bytes, i+4, this.bytes, 0, 4);
    }

    @Override
    public int lengthInBytes() {
        return 8;
    }

    @Override
    public void toBytes(byte[] bytes, int i) {
         bytes[i] = IPV4_ID;
        bytes[i+1] = MASK32;
        bytes[i+2] = NOT_CIDR;
        bytes[i+3] = IPV4_LEN;
        System.arraycopy(this.bytes, 0, bytes, i+4, 4);
    }

    @Override
    public void setValue(String value) throws SQLException {
        this.bytes = IPv4Address.of(value).toBigEndianArray();
    }

    @Override
    public String getValue() {
        return IPv4Address.of(this.bytes).toString();
    }
}
