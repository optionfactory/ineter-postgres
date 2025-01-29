package net.optionfactory.network.psql.binary;

import com.github.maltalex.ineter.range.IPv4Subnet;
import net.optionfactory.network.psql.CidrDdlType;
import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.BasicBinder;
import org.hibernate.type.descriptor.jdbc.BasicExtractor;
import org.hibernate.type.descriptor.jdbc.JdbcLiteralFormatter;
import org.hibernate.type.descriptor.jdbc.JdbcType;

import java.sql.*;

public class CidrBinaryJdbcType implements JdbcType {

    public static JdbcType INSTANCE = new CidrBinaryJdbcType();

    @Override
    public int getJdbcTypeCode() {
        return Types.OTHER;
    }

    @Override
    public int getDefaultSqlTypeCode() {
        return CidrDdlType.SQL_TYPE_CODE;
    }

    @Override
    public <T> JdbcLiteralFormatter<T> getJdbcLiteralFormatter(JavaType<T> javaType) {
        return null;
    }

    @Override
    public <X> ValueBinder<X> getBinder(JavaType<X> javaType) {
        return new BasicBinder<>(javaType, this) {
            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                final CidrPgObject holder = new CidrPgObject();
                holder.setType("cidr");
                holder.setSubnet(getJavaType().unwrap(value, IPv4Subnet.class, options));
                st.setObject(index, holder);
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options) throws SQLException {
                final CidrPgObject holder = new CidrPgObject();
                holder.setType("cidr");
                holder.setSubnet(getJavaType().unwrap(value, IPv4Subnet.class, options));
                st.setObject(name, holder);
            }
        };
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(JavaType<X> javaType) {
        return new BasicExtractor<>(javaType, this) {
            @Override
            protected X doExtract(ResultSet rs, int paramIndex, WrapperOptions options) throws SQLException {
                return getJavaType().wrap(rs.getObject(paramIndex, CidrPgObject.class).getSubnet(), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return getJavaType().wrap(statement.getObject(index, CidrPgObject.class).getSubnet(), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return getJavaType().wrap(statement.getObject(name, CidrPgObject.class).getSubnet(), options);
            }

        };
    }
}