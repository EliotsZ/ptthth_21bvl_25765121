package network;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostUriInspector {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Lỗi: Thiếu tham số <hostname> <URI>");
            return;
        }

        // 1. Phân giải Hostname
        try {
            InetAddress[] addresses = InetAddress.getAllByName(args[0]);
            System.out.println("Host: " + args[0]);
            for (InetAddress addr : addresses) {
                System.out.println("- IP: " + addr.getHostAddress());
                System.out.println("  Type: " + (addr instanceof Inet4Address ? "IPv4" : (addr instanceof Inet6Address ? "IPv6" : "Unknown")));
                System.out.println("  Loopback: " + addr.isLoopbackAddress());
                System.out.println("  Site Local: " + addr.isSiteLocalAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Lỗi Hostname: Không thể phân giải host '" + args[0] + "'");
        }

        // 2. Phân tích URI
        try {
            URI uri = new URI(args[1]);
            System.out.println("URI: " + args[1]);
            System.out.println("- Scheme: " + uri.getScheme());
            System.out.println("- Host: " + uri.getHost());
            System.out.println("- Port: " + uri.getPort());
            System.out.println("- Path: " + uri.getPath());
            System.out.println("- Query: " + uri.getQuery());
            System.out.println("- Fragment: " + uri.getFragment());
        } catch (URISyntaxException e) {
            System.err.println("Lỗi URI: " + e.getMessage());
        }
    }
}
