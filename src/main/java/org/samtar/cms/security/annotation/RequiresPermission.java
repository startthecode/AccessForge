package org.samtar.cms.security.annotation;


import org.samtar.cms.modules.shared.enums.Authorities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
    String moduleCode();
    Authorities authority();
}
