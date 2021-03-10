package ch.swb.propertyinjection.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyAdapterTest {

	private PropertyAdapter testee;

	@BeforeEach
	protected void setUp() throws Exception {
		testee = new PropertyAdapter();
	}

	@Test
	void when_fileExists_then_readPropertiesSuccessful() {
		System.setProperty(PropertyAdapter.CONFIGURATION_FILE_SYSTEM_PROPERTY, "src/test/resources/application.properties");
		testee.readPropertiesFile();
		assertThat(testee.getProperty("string.example")).isEqualTo("simple string from property with specific key");
	}

	@Test
	void when_fileNotExists_then_exception() {
		System.setProperty(PropertyAdapter.CONFIGURATION_FILE_SYSTEM_PROPERTY, "src/test/resources/does_not_exist.properties");
		assertThrows(IllegalStateException.class, () -> testee.readPropertiesFile());
	}

	@Test
	void when_fileExistsAndPropertyNotFound_then_returnNull() {
		System.setProperty(PropertyAdapter.CONFIGURATION_FILE_SYSTEM_PROPERTY, "src/test/resources/application.properties");
		testee.readPropertiesFile();
		assertThat(testee.getProperty("key.not.found")).isNull();
	}
}
