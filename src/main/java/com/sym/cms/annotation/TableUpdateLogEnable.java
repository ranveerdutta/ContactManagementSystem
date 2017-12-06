package com.sym.cms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation interface to tag a entity class as history log enable
 * @author ranveer
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface TableUpdateLogEnable {
    public String enable();
    public String fields();
}
