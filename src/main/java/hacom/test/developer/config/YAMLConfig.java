package hacom.test.developer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class YAMLConfig {

	/**
	 * Default port used when the configured port is {@code null}.
	 */
	public static final int DEFAULT_PORT = 27017;

	/**
	 * Default URI used when the configured URI is {@code null}.
	 */
	public static final String DEFAULT_URI = "mongodb+srv://admin:admin@cluster0-shard-00-02.ftuj2.mongodb.net:27017/hacomDb";

	/**
	 * Mongo server host. Cannot be set with URI.
	 */
	private String host;

	/**
	 * Mongo server port. Cannot be set with URI.
	 */
	private Integer port = null;

	/**
	 * Mongo database URI. Cannot be set with host, port and credentials.
	 */
	private String uri;

	/**
	 * Database name.
	 */
	private String database;

	/**
	 * Login user of the mongo server. Cannot be set with URI.
	 */
	private String username;

	/**
	 * Login password of the mongo server. Cannot be set with URI.
	 */
	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
