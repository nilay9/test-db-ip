package in.ankushs.dbip.api;

import com.google.common.net.InetAddresses;
import in.ankushs.dbip.exceptions.InvalidIPException;
import in.ankushs.dbip.importer.ResourceImporter;
import in.ankushs.dbip.lookup.GeoEntityLookupService;
import in.ankushs.dbip.lookup.GeoEntityLookupServiceImpl;
import in.ankushs.dbip.utils.PreConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.net.InetAddress;
import java.time.Duration;
import java.util.Set;

/**
 * 
 * Class responsible for loading data into the JVM and also an API for resolving ip.
 * @author Ankush Sharma
 */
public final class DbIpClient {
	
	private static final Logger logger = LoggerFactory.getLogger(DbIpClient.class);

	/*
	 * The dbip-city-latest.csv.gz file 
	 * */
	private final File file ;
	private final boolean fullLoadingEnabled;
	private final JedisPool jedisPool;


	//Singleton
	private final GeoEntityLookupService lookupService;
	
	/*
	 * Indicates whether the file has been loaded into the JVM.
	 * */
	private static boolean flag = false;
	
	
	/**
	 * Create a new DbIpClient . 
	 * Once an instance has been created, the allLoaded flag is set to true.
	 * Any futher initializations of the DbIpClient  will not load data into memory again.
	 * @param gzip The dbip-city-latest.csv.gz file as a File object.
	 * @throws IllegalArgumentException if {@code gzip} does not exist.
	 */
	public DbIpClient(final File gzip) {
		PreConditions.checkExpression(!gzip.exists(), "file " + gzip.getName() + " does not exist");
		this.file = gzip;
		this.fullLoadingEnabled = false;
		this.jedisPool = null;
		this.lookupService = new GeoEntityLookupServiceImpl();
		if (!flag) {
			flag = true;
			logger.info("Loading db ip into repository ");
			new ResourceImporter().load(gzip);
			logger.info("Loading finished");
		}
		else {
			logger.info(" DbIp csv file has already been loaded ");
		}
	}


	public DbIpClient(final File gzip, final JedisPool jedisPool, final boolean fullLoadingEnabled) {
		PreConditions.checkExpression(!gzip.exists(), "file " + gzip.getName() + " does not exist");
		this.file = gzip;
		this.jedisPool = jedisPool;
		this.fullLoadingEnabled = fullLoadingEnabled;

		this.lookupService = new GeoEntityLookupServiceImpl(jedisPool, fullLoadingEnabled);

		new ResourceImporter(jedisPool, fullLoadingEnabled).load(file);

	}


	/**
	 * Returns a loaded GeoEntity object for a given {@code ip}
	 * If nothing can be resolved for an {@code ip} , then the city,state and country 
	 * for the GeoEntity will be set to 'Unknown'
	 * Any futher initializations of the DbIpClient  will not load data into memory again.
	 * @param ip The ip (as String) to be resolved.
	 * @return a GeoEntity object representing city,state and province info
	 */
	public GeoEntity lookup(final String ip) {
		InetAddress inetAddress = null;
		try{
			inetAddress = InetAddresses.forString(ip);
		}
		catch(final IllegalArgumentException ex){
			logger.error("Invalid IP given",ex);
			throw new InvalidIPException("Invalid IP passed");
		}
		return lookup(inetAddress);
	}
	
	/**
	 * Returns a loaded GeoEntity object for a given {@code inetAddress}
	 * If nothing can be resolved for an {@code inetAddress} , then the city,state and country 
	 * for the GeoEntity will be set to 'Unknown'
	 * @param inetAddress The inetAddress (as InetAddress) to be resolved.
	 * @return a GeoEntity object representing city,state and province info
	 */
	public GeoEntity lookup(final InetAddress inetAddress){
		PreConditions.checkNull(inetAddress, "inetAddress cannot be null");
		return lookupService.lookup(inetAddress);
	}


	public Set<String> getCountries() {
		return lookupService.getCountries();
	}

	public Set<ProvinceCountry> getProvinceCountries() {
		return lookupService.getProvinceCountries();
	}

	public Set<CityProvinceCountry> getCityProvinceCountries() {
		return lookupService.getCityProvinceCountries();
	}



//	public Future<GeoEntity> lookupAsync(final String ip) {
//		PreConditions.checkNull(ip, "inetAddress cannot be null");
//		final InetAddress  inetAddress = InetAddresses.forString(ip);
//		return lookupService.lookupAsync(inetAddress);
//	}

	public static void main(String[] args) {
		String host = "localhost";
		int port = 6379;
//		RedisOptions redisOptions = new RedisOptions();
		Jedis jedis = new Jedis();
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(Runtime.getRuntime().availableProcessors());
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		JedisPool jedisPool = new JedisPool(jedisPoolConfig);
		DbIpClient dbIpClient = new DbIpClient(new File("/Users/nilay/Desktop/ip new file/new.csv.gz"), jedisPool, true);

		String ip = "1.6.1.0";

		System.out.println(dbIpClient.lookup(ip));

		dbIpClient.getCityProvinceCountries().forEach(it -> {
			System.out.println(it);
		});

//		BigDecimal d = new BigDecimal("123456789987654321123456789");
//		String result = d.toPlainString();
//
//		System.out.println(Double.valueOf(result));

	}

}
