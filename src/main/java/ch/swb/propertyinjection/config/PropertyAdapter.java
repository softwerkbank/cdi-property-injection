package ch.swb.propertyinjection.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.jboss.weld.exceptions.IllegalStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyAdapter.class);
	static final String CONFIGURATION_FILE_SYSTEM_PROPERTY = "application-properties-file";

	private Properties properties;

	@PostConstruct
	public void readPropertiesFile() {
		readPropertiesFile(System.getProperty(CONFIGURATION_FILE_SYSTEM_PROPERTY));
	}

	private void readPropertiesFile(String propertyFile) {
		Path path = Paths.get(propertyFile);
		if (path.toFile().exists()) {
			readPropertiesFile(path);
		} else {
			throw new IllegalStateException(String.format("No configuration '%s' found", path));
		}
	}

	private void readPropertiesFile(Path path) {
		LOGGER.info("Read configration '{}'", path);
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			properties = new Properties();
			properties.load(reader);
			LOGGER.info("{} properties read", properties.size());
		} catch (IOException e) {
			throw new UncheckedIOException(String.format("Error reading configuration file '%s'", path), e);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
