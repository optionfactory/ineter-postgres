package net.optionfactory.network.psql;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;

public class Inet4JavaType extends AbstractClassJavaType<Inet4> {

    public static final Inet4JavaType INSTANCE = new Inet4JavaType();

    public Inet4JavaType() {
        super(Inet4.class);
    }

    @Override
    public boolean useObjectEqualsHashCode() {
        return true;
    }

    @Override
    public String toString(Inet4 value) {
        return value == null ? null : value.asString();
    }

    @Override
    public Inet4 fromString(CharSequence string) {
        return string == null ? null : Inet4.parse(string.toString());
    }

    @Override
    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators indicators) {
        return indicators.getJdbcType(SqlTypes.INET);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(Inet4 value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Inet4.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (X) (Integer) value.repr();
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.asString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Inet4 wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (value instanceof Inet4 v) {
            return v;
        }
        if (value instanceof Integer repr) {
            return new Inet4(repr);
        }
        if (value instanceof String str) {
            return Inet4.parse(str);
        }
        throw unknownWrap(value.getClass());
    }

    @Override
    public long getDefaultSqlLength(Dialect dialect, JdbcType jdbcType) {
        return 19;
    }

}
