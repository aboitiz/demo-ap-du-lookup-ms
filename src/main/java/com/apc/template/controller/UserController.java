package com.apc.template.controller;


import com.apc.commons.helper.MapperHelper;
import com.apc.commons.response.BaseResponse;
import com.apc.template.config.SwaggerConfiguration;
import com.apc.template.model.User;
import com.apc.template.commons.constants.APIPathConstants;
import com.apc.template.commons.dto.UserDTO;
import com.apc.template.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIPathConstants.API_VERSION_1)
@Api(tags = {SwaggerConfiguration.USER_TAG})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(APIPathConstants.USER_BASE_PATH)
    public BaseResponse getUser() {
        BaseResponse response = new BaseResponse();
        response.setData(userService.findAll());
        log.info("transform to standard response: " + response.toJsonString(false));
        return response;
    }

    @PostMapping(APIPathConstants.USER_BASE_PATH + "/save")
    public BaseResponse saveOrUpdate(@RequestBody UserDTO user) {
        BaseResponse response = new BaseResponse(userService.saveOrUpdate(user));
        log.info("transform to standard response: " + MapperHelper.objectToJson(response));
        return response;
    }


}
