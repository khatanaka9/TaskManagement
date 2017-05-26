package models.base.vo;

import static java.util.Objects.*;

import java.util.*;
import java.util.function.*;

/**
 * VOインタフェース
 */
public interface ValueObjectInterface<T> {

	public T value();

	default public Optional<T> optionalValue() {
		return Optional.ofNullable(value());
	}

	default public T defaultValue() {
		return optionalValue().orElse(null);
	}

	default public T defaultValue(final T defaultValue) {
		return optionalValue().orElse(defaultValue);
	}

	default public T valueWithNullThrow() {
		return valueWithThrow(NullPointerException::new);
	}

	default public T valueWithThrow(
			final Supplier<? extends RuntimeException> exceptionSupplier) {
		return optionalValue().orElseThrow(exceptionSupplier);
	}

	default public <S> Optional<S> map(final Function<T, S> mapper) {
		if (isNull(mapper)) {
			throw new IllegalArgumentException("function is null");
		}
		return optionalValue().map(mapper);
	}

	default public boolean equalsValue(final T val) {
		return Objects.equals(value(), val);
	}

	default public boolean isEmpty() {
		return !optionalValue().isPresent();
	}

}
