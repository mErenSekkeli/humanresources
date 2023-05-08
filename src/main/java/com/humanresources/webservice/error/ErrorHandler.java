package com.humanresources.webservice.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class ErrorHandler {

    @Autowired
    private ErrorAttributes errorAttributes;

    private ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions.defaults();


    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(webRequest, errorAttributeOptions);
        String message = (String) attr.get("error");
        String path = (String) attr.get("path");
        int status = (int) attr.get("status");
        ApiError error = new ApiError(status, message, path);
        return error;
    }
}
