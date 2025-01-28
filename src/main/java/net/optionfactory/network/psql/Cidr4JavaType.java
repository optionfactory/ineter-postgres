package net.optionfactory.network.psql;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;

public class Cidr4JavaType extends AbstractClassJavaType<Cidr4> {

    public static final Cidr4JavaType INSTANCE = new Cidr4JavaType();

    public Cidr4JavaType() {
        super(Cidr4.class);
    }

    @Override
    public boolean useObjectEqualsHashCode() {
        return true;
    }

    @Override
    public String toString(Cidr4 value) {
        return value == null ? null : value.asString();
    }

    @Override
    public Cidr4 fromString(CharSequence string) {
        return string == null ? null : Cidr4.parse(string.toString());
    }

    @Override
    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators indicators) {
        return Cidr4JdbcType.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(Cidr4 value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Cidr4.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.asString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Cidr4 wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (value instanceof Cidr4 v) {
            return v;
        }
        if (value instanceof String str) {
            return Cidr4.parse(str);
        }
        throw unknownWrap(value.getClass());
    }

    @Override
    public long getDefaultSqlLength(Dialect dialect, JdbcType jdbcType) {
        return 19;
    }

}
