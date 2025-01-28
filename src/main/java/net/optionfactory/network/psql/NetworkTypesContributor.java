package net.optionfactory.network.psql;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

public class NetworkTypesContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions tcs, ServiceRegistry sr) {

        tcs.getTypeConfiguration().getDdlTypeRegistry().addDescriptor(new Cidr4DdlType());
        tcs.contributeJdbcType(Cidr4JdbcType.INSTANCE);
        tcs.contributeJavaType(Cidr4JavaType.INSTANCE);
        tcs.contributeJavaType(Inet4JavaType.INSTANCE);
    }

}
