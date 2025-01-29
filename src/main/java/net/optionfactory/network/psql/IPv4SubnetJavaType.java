package net.optionfactory.network.psql;

import com.github.maltalex.ineter.range.IPv4Subnet;
import net.optionfactory.network.psql.binary.CidrBinaryJdbcType;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;

public class IPv4SubnetJavaType extends AbstractClassJavaType<IPv4Subnet> {

    public static final IPv4SubnetJavaType INSTANCE = new IPv4SubnetJavaType();

    public IPv4SubnetJavaType() {
        super(IPv4Subnet.class);
    }

    @Override
    public boolean useObjectEqualsHashCode() {
        return true;
    }

    @Override
    public String toString(IPv4Subnet value) {
        return value == null ? null : value.toString();
    }

    @Override
    public IPv4Subnet fromString(CharSequence string) {
        return string == null ? null : IPv4Subnet.parse(string.toString());
    }

    @Override
    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators indicators) {
        return CidrBinaryJdbcType.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(IPv4Subnet value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (IPv4Subnet.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> IPv4Subnet wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (value instanceof IPv4Subnet v) {
            return v;
        }
        if (value instanceof String str) {
            return IPv4Subnet.parse(str);
        }
        throw unknownWrap(value.getClass());
    }

    @Override
    public long getDefaultSqlLength(Dialect dialect, JdbcType jdbcType) {
        return 19;
    }

}
