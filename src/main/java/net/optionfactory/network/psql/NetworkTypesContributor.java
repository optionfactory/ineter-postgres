package net.optionfactory.network.psql;

import net.optionfactory.network.psql.cidr.CidrPgObject;
import net.optionfactory.network.psql.inet.IPv4AddressJavaType;
import net.optionfactory.network.psql.inet.InetDdlType;
import net.optionfactory.network.psql.inet.InetJdbcType;
import net.optionfactory.network.psql.cidr.CidrDdlType;
import net.optionfactory.network.psql.cidr.CidrJdbcType;
import net.optionfactory.network.psql.cidr.IPv4SubnetJavaType;
import net.optionfactory.network.psql.inet.InetPgObject;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class NetworkTypesContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions tcs, ServiceRegistry sr) {
        tcs.getTypeConfiguration().getDdlTypeRegistry().addDescriptor(new InetDdlType());
        tcs.contributeJdbcType(InetJdbcType.INSTANCE);
        tcs.contributeJavaType(IPv4AddressJavaType.INSTANCE);

        tcs.getTypeConfiguration().getDdlTypeRegistry().addDescriptor(new CidrDdlType());
        tcs.contributeJdbcType(CidrJdbcType.INSTANCE);
        tcs.contributeJavaType(IPv4SubnetJavaType.INSTANCE);
    }
}
