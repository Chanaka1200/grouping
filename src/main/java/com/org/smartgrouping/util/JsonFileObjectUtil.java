package com.org.smartgrouping.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Component
public class JsonFileObjectUtil {
    /**
     * write given message in json
     *
     * @param httpServletResponse
     */
    public void writeJson(HttpServletResponse httpServletResponse,
                          ObjectNode node2, ObjectMapper mapper) {
        try {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "X-HTTP-Method-Override");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, DELETE,OPTION");
            // httpServletResponse.setHeader("Access-Control-Allow-Headers",
            // "X-PINGOTHER");
            httpServletResponse.setHeader("Access-Control-Max-Age", "1728000");

            mapper.writeValue(httpServletResponse.getWriter(), node2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write given message in json
     *
     * @param httpServletResponse
     * @param msg
     */
    public void writeJsonString(HttpServletResponse httpServletResponse,
                                String msg) {
        try {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "X-HTTP-Method-Override");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, DELETE,OPTION");
            // httpServletResponse.setHeader("Access-Control-Allow-Headers",
            // "X-PINGOTHER");
            httpServletResponse.setHeader("Access-Control-Max-Age", "1728000");
            httpServletResponse.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
