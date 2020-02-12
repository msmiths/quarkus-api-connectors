package com.ibm.wiotp.external.dsc.connectors.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("/api/v0002/")
public class ApplicationConfig extends Application {

    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";//$NON-NLS-1$

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<>();
		
        // REST resource providers
		classes.add(ConnectorsRestAPI.class);

		return classes;
    }

    @Override
    public Set<Object> getSingletons() {

        // Create the variable to return
        Set<Object> singletons = new HashSet<>();

        /*
         * We need to make sure that we are using the version of Jackson that we are shipping with the web module
         * rather than picking up the earlier version that is packaged in Liberty.  Specifically, we need to be using
         * classes from packages beginning with com.fasterxml.jackson and not org.codehaus.jackson.
         */
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JacksonAnnotationIntrospector();
        mapper.setAnnotationIntrospector(introspector);

        // Hide nulls
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Set the date format
        mapper.setDateFormat(getDateFormat());
        
        mapper.disable(MapperFeature.ALLOW_COERCION_OF_SCALARS);

        // Set up the Jackson provider
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        jsonProvider.setMapper(mapper);
        jsonProvider.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);

        // Add the provider to the set of singletons
        singletons.add(jsonProvider);

        return singletons;
    }

    private static DateFormat getDateFormat() {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        df.setTimeZone(TIME_ZONE);
        return df;
    }
}
