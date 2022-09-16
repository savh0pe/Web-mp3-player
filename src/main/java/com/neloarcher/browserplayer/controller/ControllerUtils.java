package com.neloarcher.browserplayer.controller;

import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtils {
    public static Map<String,String> getErrors(BindingResult bindingResult){
        return bindingResult.getFieldErrors()
                .stream().collect(
                        Collectors.toMap(
                                x->x.getField()+"Error",
                                x->x.getDefaultMessage()
                        ));
    }
}