package net.optionfactory.ineter.psql;

import net.optionfactory.ineter.psql.inet.IPv4AddressJavaType;
import net.optionfactory.ineter.psql.inet.InetDdlType;
import net.optionfactory.ineter.psql.inet.InetJdbcType;
import net.optionfactory.ineter.psql.cidr.CidrDdlType;
import net.optionfactory.ineter.psql.cidr.CidrJdbcType;
import net.optionfactory.ineter.psql.cidr.IPv4SubnetJavaType;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;


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
