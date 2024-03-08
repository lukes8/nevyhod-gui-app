package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.util.GlobalConfiguration;
import com.lukepeace.projects.nevyhodcore.service.ItemService;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.MockDataHelper;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(("/rest/api/test"))
public class TestController {
    @Autowired
    private GlobalConfiguration globalConfiguration;
    @Autowired private ItemService itemService;
    @Autowired private BCryptPasswordEncoder encoder;
    private UserService userService;
    @Autowired
    public TestController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String test(HttpServletRequest request, HttpServletResponse response) throws GeneralException {

        if (globalConfiguration.getIsDev()) {
            initTestDatabase();
        }

        return "test-success";
    }

    public void initTestDatabase() throws GeneralException {
        log.debug("Database of dummy users");
        for (UserVO obj : MockDataHelper.dummyList4User()) {
            obj.setPassword(encoder.encode(obj.getPassword()));
            log.debug("created " + userService.create(obj).toString());
        }
        log.debug("Database of dummy items");
        for (ItemVO obj : MockDataHelper.dummyList4Item(globalConfiguration.getTestUser())) {
            log.debug("created " + itemService.create(obj, obj.getId()));
        }
    }
}
