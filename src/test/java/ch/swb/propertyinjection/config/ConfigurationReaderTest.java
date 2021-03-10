package ch.swb.propertyinjection.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ConfigurationReaderTest {

	@Mock
	private PropertyAdapter propertyAdapter;

	@InjectMocks
	private ConfigurationReader testee;

	@BeforeEach
	void setUp() throws Exception {
		when(propertyAdapter.getProperty("property.empty")).thenReturn("");
		when(propertyAdapter.getProperty("property.null")).thenReturn(null);
		when(propertyAdapter.getProperty("property.string")).thenReturn("string");
		when(propertyAdapter.getProperty("property.strings")).thenReturn(" a, b, , c,");
		when(propertyAdapter.getProperty("property.boolean")).thenReturn("TRUE");
		when(propertyAdapter.getProperty("property.int")).thenReturn("42");
	}

	@Test
	void when_readStringsWithSingleValue_then_returnListWithOneEntry() {
		assertThat(testee.readStrings("property.string")).isEqualTo(Collections.singletonList("string"));
	}

	@Test
	void when_readStringsWithMultipleValues_then_returnListWithMultipleEntries() {
		assertThat(testee.readStrings("property.strings")).isEqualTo(Arrays.asList("a", "b", "c"));
	}

	@Test
	void when_readStringsWithPropertyNotExists_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readStrings("property.null"));
	}

	@Test
	void when_readStringWithValue_then_returnValue() {
		assertThat(testee.readString("property.string")).isEqualTo("string");
	}

	@Test
	void when_readStringWithEmptyValues_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readString("property.empty"));
	}

	@Test
	void when_readStringWithPropertyNotExists_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readString("property.null"));
	}

	@Test
	void when_readIntWithValue_then_returnValue() {
		assertThat(testee.readInt("property.int")).isEqualTo(42);
	}

	@Test
	void when_readIntWithEmptyValues_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readInt("property.empty"));
	}

	@Test
	void when_readIntWithPropertyNotExists_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readInt("property.null"));
	}

	@Test
	void when_readBooleanWithValue_then_returnValue() {
		assertThat(testee.readBoolean("property.boolean")).isTrue();
	}

	@Test
	void when_readBooleanWithEmptyValues_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readBoolean("property.empty"));
	}

	@Test
	void when_readBooleanWithPropertyNotExists_then_exception() {
		assertThrows(NoSuchElementException.class, () -> testee.readBoolean("property.null"));
	}

}
