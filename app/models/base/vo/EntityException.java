package models.base.vo;

import java.lang.reflect.*;

public class EntityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String field;

	private final String errorType;

	public String getField() {
		return field;
	}

	public String getErrorType() {
		return errorType;
	}

	public static EntityException duplicateException(final String message) {
		return new EntityException(String.format("%s: %s", "Entityが重複しています",
				message), null, "unique");
	}

	public static EntityException nullException(final Object o,
			final Field field) {
		return new EntityException(String.format("%s: %s#%s",
				"ManyToOneフィールドに値が設定されていません", o.getClass().getSimpleName(),
				field.getName()), o.getClass().getSimpleName() + "#"
				+ field.getName(), "null");
	}

	public EntityException(final String message, final String field,
			final String errorType) {
		super(message);
		this.field = field;
		this.errorType = errorType;
	}

	public EntityException(final String message) {
		super(message);
		this.field = null;
		this.errorType = null;
	}

	public EntityException(final Exception ex) {
		super(ex);
		this.field = null;
		this.errorType = null;
	}
}