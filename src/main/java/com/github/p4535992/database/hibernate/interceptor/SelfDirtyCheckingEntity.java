package com.github.p4535992.database.hibernate.interceptor;

import javax.persistence.Transient;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 4535992 on 13/05/2015.
 */
public abstract class SelfDirtyCheckingEntity implements DirtyAware {

    private final Map<String, String> setterToPropertyMap = new HashMap<>();

    @Transient
    private Set<String> dirtyProperties = new LinkedHashSet<>();

    public SelfDirtyCheckingEntity() {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                Method setter = descriptor.getWriteMethod();
                if (setter != null) {
                    setterToPropertyMap.put(setter.getName(), descriptor.getName());
                }
            }
        } catch (IntrospectionException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public Set<String> getDirtyProperties() {
        return dirtyProperties;
    }

    @Override
    public void clearDirtyProperties() {
        dirtyProperties.clear();
    }

    protected void markDirtyProperty() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        dirtyProperties.add(setterToPropertyMap.get(methodName));
    }
}