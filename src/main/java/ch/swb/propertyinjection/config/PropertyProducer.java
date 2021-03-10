package ch.swb.propertyinjection.config;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.weld.exceptions.IllegalStateException;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

public class PropertyProducer {

	@Inject
	private ConfigurationReader reader;

	@Produces
	@ConfigurationProperty
	public List<String> produceStringsProperty(InjectionPoint ip) {
		return reader.readStrings(getPropertyKey(ip));
	}

	@Produces
	@ConfigurationProperty
	public String produceStringProperty(InjectionPoint ip) {
		return reader.readString(getPropertyKey(ip));
	}

	@Produces
	@ConfigurationProperty
	public boolean produceBooleanProperty(InjectionPoint ip) {
		return reader.readBoolean(getPropertyKey(ip));
	}

	@Produces
	@ConfigurationProperty
	public int produceIntProperty(InjectionPoint ip) {
		return reader.readInt(getPropertyKey(ip));
	}

	private String getPropertyKey(InjectionPoint ip) {
		ConfigurationProperty property = ip.getAnnotated().getAnnotation(ConfigurationProperty.class);
		if (StringUtils.isNotBlank(property.value())) {
			return property.value();
		} else if (ip.getMember() instanceof Field) {
			return ip.getMember().getName();
		} else {
			throw new IllegalStateException(String.format("Key for property is needed if not annotated at a field!"));
		}
	}
}
