package net.optionfactory.ineter.psql;

import net.optionfactory.ineter.psql.cidr.CidrPgObject;
import net.optionfactory.ineter.psql.inet.InetPgObject;

import java.util.Properties;

public class IneterPsqlDatasourceProperties {

    public static Properties configure(Properties props) {
        props.put("datatype.cidr", CidrPgObject.class.getName());
        props.put("datatype.inet", InetPgObject.class.getName());
        final var oldBte = props.getProperty("bte");
        final var prefix = oldBte == null || oldBte.isBlank() ? "" : oldBte.strip();
        final var sep = prefix.isEmpty() ? "" : ",";
        props.put("binaryTransferEnable", String.format("%s%s%s", prefix, sep, "cidr,inet"));
        return props;
    }

    public static Properties create() {
        final var props = new Properties();
        return configure(props);
    }

}
