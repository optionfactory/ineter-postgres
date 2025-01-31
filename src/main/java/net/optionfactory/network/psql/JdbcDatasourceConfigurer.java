package net.optionfactory.network.psql;

import net.optionfactory.network.psql.cidr.CidrPgObject;
import net.optionfactory.network.psql.inet.InetPgObject;

import java.util.Properties;

public class JdbcDatasourceConfigurer {

    public static Properties datasourceConfigProps() {
        Properties props = new Properties();
        props.put("datatype.cidr", CidrPgObject.class.getName());
        props.put("datatype.inet", InetPgObject.class.getName());
        props.put("binaryTransferEnable", "cidr,inet");
        // props.put("prepareThreshold", "-1"); // force binary mode
        return props;
    }
}
