package top.b0x0.demo.sc.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.InetAddressCachePolicy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.*;

/**
 * JVM 会对 DNS 进行缓存
 * <p>
 * InetAddress缓存机制
 * java.net.InetAddress有一个缓存用于存储解析成功与解析失败的域名。默认情况下：
 * <p>
 * 如果已安装Security Manager服务，为了防止DNS欺骗攻击，解析成功的域名结果将被永久缓存。
 * 如果未安装Security Manager服务，域名解析的结果将会被缓存一段时间。域名解析失败的结果将被缓存很短的时间（默认为：10秒）以提高性能。
 * 以下两个Java安全属性控制用于主机名解析结果缓存的TTL值：
 * <p>
 * networkaddress.cache.ttl 代表域名解析成功后对解析结果的缓存时间，类型为整数，单位为秒数。 其中，-1表示永久缓存。通过sun.net.InetAddressCachePolicy.get()查看。
 * networkaddress.cache.negative.ttl (default: 10) 代表域名解析失败后对解析结果的缓存时间，类型为整数，单位为秒数。 其中，0表示从不缓存；-1表示永久缓存。通过sun.net.InetAddressCachePolicy.getNegative()查看。
 * <pre>
 *      Security.setProperty("networkaddress.cache.ttl", "0");
 *      Security.setProperty("networkaddress.cache.negative.ttl", "0");
 * </pre>
 *
 * @author wuliling Created By 2022-08-26 01:11
 **/
public class HostUtils {
    private static final Logger log = LoggerFactory.getLogger(HostUtils.class);

    public static final String SPACE = " ";
    public static final String HYPHEN = "-";
    public static final String COLON = ":";

    public static String hostFileName = getHostFile();
    public static List<?> hostFileDataLines = new ArrayList<>();

    static {
        try {
            hostFileDataLines = FileUtils.readLines(new File(hostFileName));
        } catch (IOException e) {
            log.info("Reading host file occurs error: " + e.getMessage());
        }
    }

    /**
     * 获取host文件路径
     *
     * @return /
     */
    public static String getHostFile() {
        String fileName = null;
        // 判断系统
        if (com.sun.media.jfxmediaimpl.HostUtils.isLinux()) {
            fileName = "/etc/hosts";
        } else {
            fileName = System.getenv("windir") + "\\system32\\drivers\\etc\\hosts";
        }
        return fileName;
    }

    public static void updateHost(List<String> ipDomains) {
        for (String ipDomain : ipDomains) {
            String trimIpDomain = ipDomain.trim();
            boolean contains = trimIpDomain.contains(HYPHEN);
            if (contains) {
                String[] split = trimIpDomain.split(HYPHEN);
                String ip = split[0];
                String domain = split[1];
                updateHost(ip, domain);
            }
        }
    }

    /**
     * 1.
     * 编辑$JAVA_HOME/jre/lib/secerity/java.security
     * 文件中将网络地址缓存属性（networkaddress.cache.ttl和networkaddress.cache.negative.ttl）的值修改为你想要的值；
     * 优点是一劳永逸性的修改，非编程式的解决方案; 但java.security是公用资源文件，这个方式会影响这台机器上所有的JVM
     * 2.
     * 在代码中可直接将动态配置，方式如下：
     * java.security.Security.setProperty(“propertyname”, “value”)
     * 好处时，只影响当前的JVM，不影响他人，缺点是，它是编程式的
     * <p>
     * 根据输入IP和Domain，删除host文件中的某个host配置
     *
     * @param ip     /
     * @param domain /
     * @return /
     */
    public synchronized static boolean updateHost(String ip, String domain) {
        if (StringUtils.isBlank(ip) || StringUtils.isBlank(domain)) {
            throw new IllegalArgumentException("ERROR： ip & domain must be specified");
        }
        //Step2: 解析host文件，如果指定域名不存在，则Ignore，如果已经存在，则直接删除该行配置
        List<String> newLinesList = new ArrayList<String>();
        // 标识本次文件是否需要更新，比如如果指定的IP和域名已经在host文件中存在，则不用再写文件
        boolean needUpdateFlag = true;
        for (Object curLine : hostFileDataLines) {
            String strLine = curLine.toString();
            // 将host文件中的注释或无效行, 跳过
            if (StringUtils.isEmpty(strLine)) {
                continue;
            }
            String curLineLower = strLine.toLowerCase();
            if (!curLineLower.trim().startsWith("#") && curLineLower.contains(domain.toLowerCase())) {
                needUpdateFlag = false;
            }
            newLinesList.add(strLine);
        }
        // 原文件不存在指定的IP和域名，则新增
        if (needUpdateFlag) {
            newLinesList.add("# Added by Java HostUtils.updateHost");
            // 在最后一行新增
            newLinesList.add(ip + SPACE + SPACE + SPACE + SPACE + domain);
        }

        //Step3: 将更新写入host文件中去
        if (needUpdateFlag) {
            try {
                FileUtils.writeLines(new File(hostFileName), newLinesList);
            } catch (Exception e) {
                log.error("Updating host file occurs error: " + e.getMessage(), e);
                return false;
            }
        }
        return true;
    }

    /**
     * 清除清除DNS缓存
     */
    public void flushdns() {
        try {
            Runtime.getRuntime().exec("ipconfig /flushdns");
        } catch (IOException e) {
            log.error("ERROR: call runtime to flushdns exception: " + e.getMessage());
        }
    }

    /**
     * 查看InetAddress中缓存的DNS信息
     *
     * @param cacheName /
     * @throws Exception /
     */
    @SuppressWarnings("unchecked")
    private static void printDNSCache(String cacheName) throws Exception {
        Class<InetAddress> klass = InetAddress.class;
        Field acf = klass.getDeclaredField(cacheName);
        acf.setAccessible(true);
        Object addressCache = acf.get(null);
        Class<?> cacheKlass = addressCache.getClass();
        Field cf = cacheKlass.getDeclaredField("cache");
        cf.setAccessible(true);
        Map<String, Object> cache = (Map<String, Object>) cf.get(addressCache);
        for (Map.Entry<String, Object> hi : cache.entrySet()) {
            Object cacheEntry = hi.getValue();
            Class<?> cacheEntryKlass = cacheEntry.getClass();
            Field expf = cacheEntryKlass.getDeclaredField("expiration");
            expf.setAccessible(true);
            long expires = (Long) expf.get(cacheEntry);

            Field af = cacheEntryKlass.getDeclaredField("address");
            af.setAccessible(true);
            InetAddress[] addresses = (InetAddress[]) af.get(cacheEntry);
            List<String> ads = new ArrayList<String>(addresses.length);
            for (InetAddress address : addresses) {
                ads.add(address.getHostAddress());
            }
            log.info(hi.getKey() + " " + new Date(expires) + " " + ads);
        }
    }

    /**
     * 移出InetAddress中缓存的DNS信息
     *
     * @param hostname /
     * @throws Exception /
     * @see InetAddressCachePolicy
     */
    @SuppressWarnings("unchecked")
    public static void removeDnsCachePolicy(String hostname) throws Exception {
        Class<?> inetAddressClass = InetAddress.class;
        final Field cacheField = inetAddressClass.getDeclaredField("addressCache");
        cacheField.setAccessible(true);
        final Object obj = cacheField.get(inetAddressClass);
        Class<?> cacheClazz = obj.getClass();
        final Field cacheMapField = cacheClazz.getDeclaredField("cache");
        cacheMapField.setAccessible(true);
        final Map<String, Object> cacheMap = (Map<String, Object>) cacheMapField.get(obj);
        cacheMap.remove(hostname);
    }
}
