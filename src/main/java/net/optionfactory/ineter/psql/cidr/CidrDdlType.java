package net.optionfactory.ineter.psql.cidr;

import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.sql.DdlType;

public class CidrDdlType implements DdlType {

    public static int SQL_TYPE_CODE = 100_001;

    @Override
    public int getSqlTypeCode() {
        return SQL_TYPE_CODE;
    }

    @Override
    public String getRawTypeName() {
        return "cidr";
    }

    @Override
    public String getTypeName(Long size, Integer precision, Integer scale) {
        return "cidr";
    }

    @Override
    public String getCastTypeName(JdbcType jdbcType, JavaType<?> javaType) {
        return "cidr";
    }

    @Override
    public String getCastTypeName(JdbcType jdbcType, JavaType<?> javaType, Long length, Integer precision, Integer scale) {
        return "cidr";
    }

}
