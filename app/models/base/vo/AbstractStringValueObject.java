package models.base.vo;

import org.apache.commons.lang.*;

/** 文字列項目用基底VO */
public abstract class AbstractStringValueObject extends AbstractValueObject
		implements ValueObjectInterface<String> {

	@Override
	public String defaultValue() {
		return optionalValue().orElse(StringUtils.EMPTY);
	}

	public String formatedValue(final String format) {
		return formatedValue(format, "");
	}

	public String formatedValue(final String format, final String defaultValue) {
		return optionalValue().map((val) -> String.format(format, val)).orElse(
				defaultValue);
	}

	public String removeNewLine() {
		return optionalValue().map(
				val -> val.replaceAll("\r\n", "").replaceAll("\n", "")).orElse(
				"");
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isEmpty(value());
	}

	@Override
	public boolean equalsValue(final String val) {
		return StringUtils.equals(value(), val);
	}

	public boolean matches(final String regex) {
		return optionalValue().map((val) -> val.matches(regex)).orElse(false);
	}

	public boolean isHalfWidth() {
		return halfWidthLength(this.value()) == this.value().length();
	}

	public boolean isNumeric() {
		return optionalValue().map(NumberUtils::isNumber).orElse(false);
	}

	public Integer toInt() {
		return (Integer) optionalValue().map((val) -> {
			try {
				return Integer.parseInt(val);
			} catch (final NumberFormatException e) {
				try {
					throw new Exception(e);
				} catch (final Exception e1) {
					e1.printStackTrace();
				}
			}
			return val;
		}).orElse(null);
	}

	public static int halfWidthLength(final String string) {
		if (string == null) {
			return 0;
		}
		int length = 0;
		for (int i = 0; i < string.length(); ++i) {
			length = length + ((string.charAt(i) > 255) ? 2 : 1);
		}
		return length;
	}

}