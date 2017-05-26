package models.base.vo;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import javax.persistence.*;

import org.dom4j.tree.*;

public class ReflectionUtil {

	public static <T extends AbstractValueObject> Field getValueField(final T vo) {
		return getValueField(getFields(vo));
	}

	/** 指定フィールド内に含まれる value フィールドの取得 */
	public static Field getValueField(final Field field) {
		return getValueField(getFields(field));
	}

	/** 指定フィールド内に含まれる value フィールドの取得 */
	private static Field getValueField(final List<Field> fields) {
		return fields.stream().filter(field -> field.getName().equals("value"))
				.findAny().orElse(null);
	}

	public static <T extends AbstractValueObject> List<AbstractValueObject> getValueObjects(
			final T vo) {
		return getFields(vo).stream().map(field -> {
			field.setAccessible(true);
			try {
				if (field.get(vo) instanceof AbstractValueObject) {
					return (AbstractValueObject) field.get(vo);
				}
			} catch (final Exception e) {
			}
			return null;
		}).filter(valueObject -> valueObject != null)
				.collect(Collectors.toList());
	}

	/** 引数オブジェクトに含まれるVOフィールド一覧の取得 */
	public static List<Field> getValueObjectFields(final Object o) {
		return getFields(o)
				.stream()
				.filter(field -> {
					field.setAccessible(true);
					// VO判断は、AbstractValueObjectを継承しているかどうか
					return AbstractValueObject.class.isAssignableFrom(field
							.getType());
				}).collect(Collectors.toList());
	}

	/** 引数オブジェクトに含まれる@Columnアノテーションの付加されたフィールド一覧の取得 */
	public static List<Field> getColumnAnnotationFields(final Object o) {
		return getFieldsByAnnotationType(o, Column.class);
	}

	public static List<Field> getColumnAnnotationFields(final Class<?> klass) {
		return getFieldsByAnnotationType(klass, Column.class);
	}

	public static List<Field> getManyToOneAnnotationFields(final Object o) {
		return getFieldsByAnnotationType(o, ManyToOne.class);
	}

	private static List<Field> getFieldsByAnnotationType(final Object o,
			final Class<? extends Annotation> annotationClass) {
		return getFieldsByAnnotationType(o.getClass(), annotationClass);
	}

	private static List<Field> getFieldsByAnnotationType(final Class<?> klass,
			final Class<? extends Annotation> annotationClass) {
		return getFields(klass).stream().filter(field -> {
			field.setAccessible(true);
			return field.getDeclaredAnnotation(annotationClass) != null;
		}).collect(Collectors.toList());
	}

	private static List<Field> getFields(final Class<?> klass) {
		final List<Field> fields = new ArrayList<>();
		final Class<?> superclass = klass.getSuperclass();
		if (AbstractEntity.class.isAssignableFrom(superclass)
				&& superclass.equals(AbstractEntity.class) == false
				|| AbstractValueObject.class.isAssignableFrom(superclass)
				&& superclass.equals(AbstractValueObject.class) == false) {
			fields.addAll(getFields(superclass));
		}
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		return fields;
	}

	private static List<Field> getFields(final Object o) {
		return getFields(o.getClass());
	}

	private static List<Field> getFields(final Field field) {
		return getFields(field.getType());
	}

	public static Class<?> getCallerClass() {
		for (final StackTraceElement element : Thread.currentThread()
				.getStackTrace()) {
			try {
				final Class<?> klass = Class.forName(element.getClassName());
				if (AbstractEntity.class.isAssignableFrom(klass)
						&& klass.equals(AbstractEntity.class) == false) {
					return klass;
				}
			} catch (final ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		throw new EntityException("呼び出し元エンティティクラスが見つかりません");
	}
}
