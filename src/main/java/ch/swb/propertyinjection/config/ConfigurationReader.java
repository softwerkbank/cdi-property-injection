package ch.swb.propertyinjection.config;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ConfigurationReader {
	private static final String KEY_NOT_FOUND_OR_VALUE_IS_EMPTY = "Key '%s' not found or value is empty";

	@Inject
	private PropertyAdapter propertyAdapter;

	public List<String> readStrings(String key) {
		String value = propertyAdapter.getProperty(key);
		if (StringUtils.isNotBlank(value)) {
			return Stream.of(value)
					.map(s -> s.replaceAll("\"", ""))
					.flatMap(s -> Stream.of(s.split(",")))
					.filter(StringUtils::isNotBlank)
					.map(String::trim)
					.collect(Collectors.toList());
		} else {
			throw new NoSuchElementException(String.format(KEY_NOT_FOUND_OR_VALUE_IS_EMPTY, key));
		}
	}

	public String readString(String key) {
		String value = propertyAdapter.getProperty(key);
		if (StringUtils.isNotBlank(value)) {
			return value;
		} else {
			throw new NoSuchElementException(String.format(KEY_NOT_FOUND_OR_VALUE_IS_EMPTY, key));
		}
	}

	public boolean readBoolean(String key) {
		String value = propertyAdapter.getProperty(key);
		if (StringUtils.isNotBlank(value)) {
			return Boolean.parseBoolean(value);
		} else {
			throw new NoSuchElementException(String.format(KEY_NOT_FOUND_OR_VALUE_IS_EMPTY, key));
		}
	}

	public int readInt(String key) {
		String value = propertyAdapter.getProperty(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		} else {
			throw new NoSuchElementException(String.format(KEY_NOT_FOUND_OR_VALUE_IS_EMPTY, key));
		}
	}
}
