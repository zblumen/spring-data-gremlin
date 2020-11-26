package com.microsoft.spring.data.gremlin.conversion.source.code;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.conversion.source.GremlinSource;
import com.microsoft.spring.data.gremlin.exception.GremlinInvalidEntityIdFieldException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.microsoft.spring.data.gremlin.common.Constants.GREMLIN_PROPERTY_CLASSNAME;

public class AbstractGremlinCodeSource<T> implements GremlinSource<T> {
    @Setter
    private Object id;

    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private Field idField;

    @Getter
    private Class<T> domainClass;

    @Getter
    @Setter
    private Map<String, Object> properties;

    protected AbstractGremlinCodeSource() {
        this.properties = new HashMap<>();
    }

    protected AbstractGremlinCodeSource(Class<T> domainClass) {
        this.domainClass = domainClass;
        this.properties = new HashMap<>();

        setProperty(GREMLIN_PROPERTY_CLASSNAME, domainClass.getName());
    }

    @Override
    public Optional<Object> getId() {
        return Optional.ofNullable(this.id);
    }

    /**
     * The type of Id keep the consistency with the result from gremlin server, for generate query correctly. So if the
     * id is ${@link GeneratedValue}, which may have different type against entity id field.
     *
     * @param id the given id from query.
     */
    @Override
    public void setId(Object id) {
        final Field idField = getIdField();

        if (idField == null) {
            throw new GremlinInvalidEntityIdFieldException("Id Field of GremlinSource cannot be null");
        }

        if (idField.isAnnotationPresent(GeneratedValue.class) && id instanceof String) {
            try {
                this.id = Long.valueOf((String) id); // Gremlin server default id type is Long.
            } catch (NumberFormatException ignore) {
                this.id = id;
            }
        } else {
            this.id = id;
        }
    }

    private boolean hasProperty(String key) {
        return this.properties.get(key) != null;
    }

    @Override
    public void setProperty(String key, Object value) {
        if (this.hasProperty(key) && value == null) {
            this.properties.remove(key);
        } else {
            this.properties.put(key, value);
        }
    }


}
