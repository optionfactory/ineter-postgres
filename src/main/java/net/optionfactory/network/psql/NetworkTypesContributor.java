package net.optionfactory.network.psql;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

public class NetworkTypesContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions tcs, ServiceRegistry sr) {

        tcs.getTypeConfiguration().getDdlTypeRegistry().addDescriptor(new CidrDdlType());
        tcs.getTypeConfiguration().getDdlTypeRegistry().addDescriptor(new InetDdlType());
        tcs.contributeJdbcType(CidrJdbcType.INSTANCE);
        tcs.contributeJdbcType(InetJdbcType.INSTANCE);
        tcs.contributeJavaType(IPv4AddressJavaType.INSTANCE);
        tcs.contributeJavaType(IPv4SubnetJavaType.INSTANCE);
    }

}
