package net.optionfactory.network.psql;

import java.util.regex.Pattern;

public record Cidr4(int network, byte len) {

    @Override
    public String toString() {
        return asString();
    }

    public String asString() {
        return String.format("%s.%s.%s.%s/%s",
                network >>> 24 & 0xff,
                network >>> 16 & 0xff,
                network >>> 8 & 0xff,
                network & 0xff,
                len
        );
    }

    private static final Pattern CIDR_PATTERN = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,2})");

    public static Cidr4 parse(String v) {
        final var m = CIDR_PATTERN.matcher(v);
        if (!m.matches()) {
            throw new IllegalArgumentException(v);
        }
        final var o1 = (Integer.parseInt(m.group(1)) & 0xff) << 24;
        final var o2 = (Integer.parseInt(m.group(2)) & 0xff) << 16;
        final var o3 = (Integer.parseInt(m.group(3)) & 0xff) << 8;
        final var o4 = Integer.parseInt(m.group(4)) & 0xff;
        final var len = Byte.parseByte(m.group(5));
        return new Cidr4(o1 | o2 | o3 | o4, len);
    }

}
