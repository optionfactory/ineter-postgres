package net.optionfactory.network.psql.inet;

import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.sql.DdlType;

public class InetDdlType implements DdlType {

    public static int SQL_TYPE_CODE = 100_002;
    
    
    @Override
    public int getSqlTypeCode() {
        return SQL_TYPE_CODE;
    }

    @Override
    public String getRawTypeName() {
        return "inet";
    }

    @Override
    public String getTypeName(Long size, Integer precision, Integer scale) {
        return "inet";
    }

    @Override
    public String getCastTypeName(JdbcType jdbcType, JavaType<?> javaType) {
        return "inet";
    }

    @Override
    public String getCastTypeName(JdbcType jdbcType, JavaType<?> javaType, Long length, Integer precision, Integer scale) {
        return "inet";
    }

}
