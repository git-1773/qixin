package com.show.qixin.api.common.config.web.plugin.annotation;

import com.show.qixin.api.common.config.web.plugin.CommonData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiJsonResult {

    String[] value();

    String name() default "";

    String type() default CommonData.RESULT_TYPE_NORMAL_FINAL;

}
