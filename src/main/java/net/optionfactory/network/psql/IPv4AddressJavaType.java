package net.optionfactory.network.psql;

import com.github.maltalex.ineter.base.IPv4Address;
import net.optionfactory.network.psql.binary.InetBinaryJdbcType;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;

public class IPv4AddressJavaType extends AbstractClassJavaType<IPv4Address> {

    public static final IPv4AddressJavaType INSTANCE = new IPv4AddressJavaType();

    public IPv4AddressJavaType() {
        super(IPv4Address.class);
    }

    @Override
    public boolean useObjectEqualsHashCode() {
        return true;
    }

    @Override
    public String toString(IPv4Address value) {
        return value == null ? null : value.toString();
    }

    @Override
    public IPv4Address fromString(CharSequence string) {
        return string == null ? null : IPv4Address.of(string.toString());
    }

    @Override
    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators indicators) {
        return InetBinaryJdbcType.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(IPv4Address value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (IPv4Address.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (byte[].class.isAssignableFrom(type)) {
            return (X) value.toBigEndianArray();
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (X) (Integer) value.toInt();
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> IPv4Address wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (value instanceof IPv4Address v) {
            return v;
        }
        if (value instanceof byte[] v) {
            return IPv4Address.of(v);
        }
        if (value instanceof Integer repr) {
            return IPv4Address.of(repr);
        }
        if (value instanceof String str) {
            return IPv4Address.of(str);
        }
        throw unknownWrap(value.getClass());
    }

    @Override
    public long getDefaultSqlLength(Dialect dialect, JdbcType jdbcType) {
        return 19;
    }

}
