package ch.swb.propertyinjection;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.propertyinjection.config.ConfigurationProperty;
import jakarta.inject.Inject;

public class ApplicationConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	@Inject
	@ConfigurationProperty
	private int intExample;

	@Inject
	@ConfigurationProperty("int.example")
	private int intExampleWithSpecificKey;

	@Inject
	@ConfigurationProperty
	private boolean booleanExample;

	@Inject
	@ConfigurationProperty("boolean.example")
	private boolean booleanExampleWithSpecificKey;

	@Inject
	@ConfigurationProperty
	private String stringExample;

	@Inject
	@ConfigurationProperty("string.example")
	private String stringExampleWithSpecificKey;

	@Inject
	@ConfigurationProperty
	private List<String> stringListExample;

	@Inject
	@ConfigurationProperty("string.list.example")
	private List<String> stringListExampleWithSpecificKey;

	private int intParamExample;
	private boolean booleanParamExampleWithSpecificKey;
	private String stringParamExample;
	private List<String> stringListParamExampleWithSpecificKey;

	@Inject
	public ApplicationConfiguration(@ConfigurationProperty("int.param.example") int intParamExample,
			@ConfigurationProperty("boolean.param.example") boolean booleanParamExampleWithSpecificKey,
			@ConfigurationProperty("string.param.example") String stringParamExample,
			@ConfigurationProperty("string.list.param.example") List<String> stringListParamExampleWithSpecificKey) {
		this.intParamExample = intParamExample;
		this.booleanParamExampleWithSpecificKey = booleanParamExampleWithSpecificKey;
		this.stringParamExample = stringParamExample;
		this.stringListParamExampleWithSpecificKey = stringListParamExampleWithSpecificKey;
	}

	public void logPropertyValues() {
		LOGGER.info("--------Properties without Key--------");
		LOGGER.info("intExample = {}", intExample);
		LOGGER.info("booleanExample = {}", booleanExample);
		LOGGER.info("stringExample = {}", stringExample);
		LOGGER.info("stringListExample = {}", stringListExample);
		LOGGER.info("");

		LOGGER.info("--------Properties with Key--------");
		LOGGER.info("intExampleWithSpecificKey = {}", intExampleWithSpecificKey);
		LOGGER.info("booleanExampleWithSpecificKey = {}", booleanExampleWithSpecificKey);
		LOGGER.info("stringExampleWithSpecificKey = {}", stringExampleWithSpecificKey);
		LOGGER.info("stringListExampleWithSpecificKey = {}", stringListExampleWithSpecificKey);
		LOGGER.info("");

		LOGGER.info("--------Constructor Params--------");
		LOGGER.info("intParamExample = {}", intParamExample);
		LOGGER.info("booleanParamExampleWithSpecificKey = {}", booleanParamExampleWithSpecificKey);
		LOGGER.info("stringParamExample = {}", stringParamExample);
		LOGGER.info("stringListParamExampleWithSpecificKey = {}", stringListParamExampleWithSpecificKey);
	}

}
