package com.springbootwebmvc.employee.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    // ErrorAttributes object is used to save all error attributes value.
    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /* Return the error page path. */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    // Handle the /error path invoke.
    @RequestMapping("/error")
    /* @ResponseBody annotation will return the error page content instead of the template error page name. */
    @ResponseBody
    public String processError(HttpServletRequest request, WebRequest webRequest) {

        // Get error status code.
        Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(statusCode.intValue() == 500)
        {
            // If you want to return template error page, then remove the @ResponseBody annotation of this method.
            return "error-500.html";
        }else
        {
            // Get error message.
            String message = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

            // Get exception object.
            Exception exception = (Exception)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

            // Get error stack trace map object.
            Map<String, Object> body = errorAttributes.getErrorAttributes(webRequest, true);
            // Extract stack trace string.
            String trace = (String) body.get("trace");

            StringBuffer retBuf = new StringBuffer();
            retBuf.append("<pre>");

            if(statusCode != null)
            {
                retBuf.append("Status Code : ");
                retBuf.append(statusCode);
            }

            if(message != null && message.trim().length() > 0)
            {
                retBuf.append("\n\rError Message : ");
                retBuf.append(message);
            }

            if(trace != null){
                retBuf.append("\n\rStack Trace : ");
                retBuf.append(trace);
            }

            retBuf.append("</pre>");

            return retBuf.toString();
        }

    }
}