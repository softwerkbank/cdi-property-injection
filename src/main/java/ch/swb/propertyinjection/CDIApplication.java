package ch.swb.propertyinjection;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class CDIApplication {

	public static void main(String... args) {
		try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			ApplicationConfiguration applicationConfiguration = container.select(ApplicationConfiguration.class).get();
			applicationConfiguration.logPropertyValues();
		}
	}

}
