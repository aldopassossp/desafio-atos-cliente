package net.atos.api.cliente.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
	@Parameter(name = "page", required = false, example = "12"),
	@Parameter(name = "size",  required = false, example = "12"),
	@Parameter(name = "dataInicio", required = false, example = "01-12-2021"),
	@Parameter(name = "dataFim", required = false, example = "02-12-2021")
        })
public @interface PageableBinding {

}

