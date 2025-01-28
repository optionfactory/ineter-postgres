package net.optionfactory.network.psql;

import java.util.regex.Pattern;

public record Inet4(int repr) {

    @Override
    public String toString() {
        return asString();
    }

    public String asString() {
        return String.format("%s.%s.%s.%s",
                repr >>> 24 & 0xff,
                repr >>> 16 & 0xff,
                repr >>> 8 & 0xff,
                repr & 0xff
        );
    }

    private static final Pattern IPV4_PATTERN = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

    public static Inet4 parse(String v) {
        final var m = IPV4_PATTERN.matcher(v);
        if (!m.matches()) {
            throw new IllegalArgumentException(v);
        }
        final var o1 = (Integer.parseInt(m.group(1)) & 0xff) << 24;
        final var o2 = (Integer.parseInt(m.group(2)) & 0xff) << 16;
        final var o3 = (Integer.parseInt(m.group(3)) & 0xff) << 8;
        final var o4 = Integer.parseInt(m.group(4)) & 0xff;
        return new Inet4(o1 | o2 | o3 | o4);
    }

}
