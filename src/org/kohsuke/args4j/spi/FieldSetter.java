package org.kohsuke.args4j.spi;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * {@link Setter} that sets to a {@link Field}.
 *
 * @author Kohsuke Kawaguchi
 */
public final class FieldSetter implements Setter {
    private final Field f;
    private final Object bean;

    public FieldSetter(Object bean, Field f) {
        this.bean = bean;
        this.f = f;
    }

    public Class getType() {
        return f.getType();
    }
    
    public boolean isMultiValued() {
        // a field can only store one value. a collection field is handled via MultiValueFieldSetter
    	return false;
    }

    public FieldSetter asFieldSetter() {
        return new FieldSetter(bean,f);
    }

    public AnnotatedElement asAnnotatedElement() {
        return f;
    }

    public void addValue(Object value) {
        try {
            f.set(bean,value);
        } catch (IllegalAccessException _) {
            // try again
            f.setAccessible(true);
            try {
                f.set(bean,value);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessError(e.getMessage());
            }
        }
    }

    public Object getValue() {
        try {
            return f.get(bean);
        } catch (IllegalAccessException _) {
            // try again
            f.setAccessible(true);
            try {
                return f.get(bean);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessError(e.getMessage());
            }
        }
    }
}
