package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;


import com.lukepeace.projects.common.annotations.CustomPageableAsQueryParam;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.common.security.SecurityContextHolderProvider;
import com.lukepeace.projects.common.vo.UserDetailVO;
import com.lukepeace.projects.common.vo.UserLoginVO;
import com.lukepeace.projects.common.vo.UserRegisterVO;
import com.lukepeace.projects.nevyhodcore.entity.UserRole;
import com.lukepeace.projects.nevyhodcore.service.UserAuthService;
import com.lukepeace.projects.nevyhodcore.service.UserRoleService;
import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.AuditInfoHelper;
import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(("/rest/api/user"))
@Tag(name = "users", description = "the user API")
@CrossOrigin
public class UserController {
    @Autowired
    private AuditInfoHelper auditInfoHelper;
    @Autowired
    private SecurityContextHolderProvider securityContextHolderProvider;
    @Autowired
    private UserRoleService userRoleService;
    private UserService userService;
    private UserAuthService userAuthService;
    @Autowired
    public UserController(@Qualifier("userService") UserService userService, @Qualifier("userAuthService") UserAuthService userAuthService) {
        this.userService = userService;
        this.userAuthService = userAuthService;
    }
    @PostMapping("/login")
    public UserDetailVO login(@RequestBody UserLoginVO userLogin) throws GeneralException {
        return userAuthService.login(userLogin);
    }
    @PostMapping("/register")
    public UserRegisterVO register(@RequestBody UserRegisterVO userRegister) throws GeneralException {
        return userAuthService.register(userRegister);
    }
    @GetMapping("/login-info")
    public String loginInfo(HttpServletRequest request, HttpServletResponse response) {
        String username = securityContextHolderProvider.getCurrentUser().getUsername();
        String roles = securityContextHolderProvider.getCurrentUser().getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.joining(", "));
        log.info("Audit info : " + auditInfoHelper.getRemoteAddress());
        log.info("Audit info : " + auditInfoHelper.getRequestURI());
        log.info("Audit info : " + roles);
        return username;
    }
    @GetMapping()
    @PreAuthorize(Permission.ACCESS_USER_DATA)
    @Operation(summary = "Get all users", description = "For valid response try pageable with different sort attribute value for eg. email", tags = "users")
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Invalid fields filled"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @CustomPageableAsQueryParam
    public Page<UserVO> getAll(@Parameter(hidden = true) Pageable pageable,
                               @RequestParam(required = false, defaultValue = "text") String email,
                               @RequestParam(required = false, defaultValue = "text") String name,
                               @RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDate
    ) throws GeneralException {
        UserFilter filter = new UserFilter(pageable);
        filter.setEmail(email);
        filter.setName(name);
        filter.setCreatedDate(createdDate);
        Page<UserVO> lst = userService.findAll(filter);
        return lst;
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize(Permission.ACCESS_USER_DATA)
    public UserVO update(@RequestBody UserVO obj) throws GeneralException {
        return userService.update(obj);
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize(Permission.ACCESS_USER_DATA)
    public UserVO create(@RequestBody UserVO obj) throws GeneralException {
        return userService.create(obj);
    }

    @GetMapping("user-roles")
    @PreAuthorize(Permission.ACCESS_USER_DATA)
    public List<UserRole> getAllUserRoles() throws GeneralException {
        return userRoleService.findAll();
    }


    // TODO updateUser(roles)
}
