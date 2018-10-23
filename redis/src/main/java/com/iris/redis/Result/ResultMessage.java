package com.iris.redis.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultMessage {

    public static Map<Integer, String> RESULTMSGBOX = new HashMap<Integer, String>();

    private static final String SUCCESSMSG_ACTION_SUCCESS = "Completed successfully";

    private static final String SERVER_ERROR_MSG = "Error in olgmnamein micro-service";

    static {
        RESULTMSGBOX.put(ResultCode.SUCCESS, SUCCESSMSG_ACTION_SUCCESS);
        RESULTMSGBOX.put(ResultCode.SERVER_ERROR, SERVER_ERROR_MSG);
    }

}
