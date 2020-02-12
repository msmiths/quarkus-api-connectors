package com.ibm.wiotp.external.dsc.connectors.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ConnectorsRestAPI.PATH_ORGANIZATIONS_API + "/{" + ConnectorsRestAPI.PATH_PARAM_ORG_ID + "}/" + ConnectorsRestAPI.PATH_CONNECTORS_API)
@Produces(MediaType.APPLICATION_JSON)
public class ConnectorsRestAPI { // extends AbstractRestAPI {

    private static final String CLASS = ConnectorsRestAPI.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASS);

    public static final String PATH_PARAM_ORG_ID = "orgId";
    public static final String PATH_ORGANIZATIONS_API = "organizations";
    public static final String PATH_CONNECTORS_API = "historianconnectors";

    public static final String REGEX_ORG_ID = "^[A-Za-z0-9]{6,6}$";
    public static final String REGEX_NAME = "^(?![ ]+)[\\w \\-\\_\\.]{1,128}$";
    public static final String REGEX_ENABLED = "^(true|false)$";
    public static final String REGEX_TYPE = "^(eventstreams|cloudant|db2|postgres)$";
    public static final String REGEX_SERVICE_ID = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    
    public static final String QUERY_PARAM_BOOKMARK = "_bookmark";
    public static final String QUERY_PARAM_LIMIT = "_limit";
    public static final String QUERY_PARAM_SORT = "_sort";
    public static final String QUERY_PARAM_NAME = "name";
    public static final String QUERY_PARAM_ENABLED = "enabled";
    public static final String QUERY_PARAM_TYPE = "type";
    public static final String QUERY_PARAM_SERVICE_ID = "serviceId";

    @GET
    public Response getConnectors(
        @NotNull(message="{CUDVS0012E}")
        @Pattern(regexp=REGEX_ORG_ID, message="{CUDVS0103E}")
        @PathParam(PATH_PARAM_ORG_ID) final String orgId,
        @QueryParam(QUERY_PARAM_BOOKMARK) String bookmark,
        @QueryParam(QUERY_PARAM_LIMIT) String limit,
        @QueryParam(QUERY_PARAM_SORT) String sortProps,
        @Pattern(regexp=REGEX_NAME, message="{CUDVS0107E}")
        @QueryParam(QUERY_PARAM_NAME) String name,
        @Pattern(regexp=REGEX_ENABLED, message="{CUDVS0109E}")
        @QueryParam(QUERY_PARAM_ENABLED) String enabled,
        @Pattern(regexp=REGEX_TYPE, message="{CUDVS0108E}")
        @QueryParam(QUERY_PARAM_TYPE) String type,
        @Pattern(regexp=REGEX_SERVICE_ID, message="{CUDVS0111E}")
        @QueryParam(QUERY_PARAM_SERVICE_ID) String serviceId
    ) {

        final String METHOD = "getConnectors";
        LOG.logp(Level.INFO, CLASS, METHOD, String.format("[%s]: Get connectors. type: %s _bookmark: %s, limit: %s, sortProps: %s, name: %s, serviceId: %s", orgId, type, bookmark, limit, sortProps, name, serviceId));

        Response response = Response.ok("{\"message\": \"Hello World!!!\"}").build();
        return response;
    }
}