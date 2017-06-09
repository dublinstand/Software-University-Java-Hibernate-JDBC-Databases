package com.airconditioners.core.annotations;


import java.lang.annotation.*;


//add an annotation for fields that are insertable with ElementType.FIELD
//with this annotation we can insert the dependency manually
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Insert {
}
