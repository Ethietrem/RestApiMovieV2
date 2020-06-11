package pl.wsb.students.consts;

public class ApiEndpoints {
    private static final String QUERY_PARAM_ID = "/{id}";

    public static final String AUTHENTICATE = "/authenticate";                                                          //POST
    public static final String LIBRARY = "/library";                                                                    //POST & DELETE
    public static final String MOVIE = "/movie";                                                                        //GET % POST & PUT
    public static final String USER = "/user";                                                                          //POST

    public static final String MOVIE_RATE = "/rate";                                                                    //POST
    public static final String MOVIE_COMMENT = "/comment";                                                              //POST
    public static final String MOVIE_ID_ACCEPT = ApiEndpoints.QUERY_PARAM_ID + "/accept";                               //PUT
    public static final String MOVIE_ID_REJECT = ApiEndpoints.QUERY_PARAM_ID + "/reject";                               //PUT
    public static final String USER_ID_UPDATE = ApiEndpoints.QUERY_PARAM_ID;                                            //PUT
    public static final String USER_ID_LOGOUT = ApiEndpoints.QUERY_PARAM_ID + "/logout";                                //PUT

    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_SEARCH = "search";
}