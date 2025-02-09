package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Role;
import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.persistence.model.UserRole;
import com.airtime.logbook_service.service.RoleService;
import com.airtime.logbook_service.service.UserProfileService;
import com.airtime.logbook_service.service.UserRoleService;
import com.airtime.logbook_service.service.UserService;
import com.airtime.logbook_service.web.dto.UserProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.*;

import static java.util.Map.of;

@RestController
public class UserController {
    private final ClientRegistration registration;
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;


    public UserController(ClientRegistrationRepository registrations, UserService userService, UserProfileService userProfileService, RoleService roleService, UserRoleService userRoleService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok().body(user.getAttributes());
        }
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(value = "/api/private/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.findAll();
        return (userList != null ? new ResponseEntity<>(userList, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /*@Secured("ROLE_SUPER_ADMIN")
    @GetMapping(value = "/api/private/user-profiles")
    public ResponseEntity<List<UserProfileDTO>> getUserProfiles() {
        List<UserProfileDTO> userProfileDTOList = userProfileService.findAllUserProfiles();
        return (userProfileDTOList != null ? new ResponseEntity<>(userProfileDTOList, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }*/

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(value = "/api/private/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        User user = userService.findById(id);
        return (user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping(value = "/api/private/user")
    public ResponseEntity<Boolean> saveUser(@RequestBody @Validated User user) {
        boolean userSaved = false;

        User existingUser = userService.findById(user.getId());

        if (existingUser != null) {
            existingUser.setInUse(user.isInUse());
        }

        userSaved = userService.save(user);

        return ((userSaved) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping(value = "/api/private/user/roles/add")
    public ResponseEntity<Boolean> addRole(@RequestBody @Validated UserRole userRole) {
        return ((userRoleService.save(userRole)) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping(value = "/api/private/user/roles/remove")
    public ResponseEntity<Boolean> removeRole(@RequestBody @Validated UserRole userRole) {
        return ((userRoleService.delete(userRole)) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(value = "/api/private/roles")
    public ResponseEntity<List<Role>> getUserRoles() {
        List<Role> roleList = roleService.findAllRoles();
        return (roleList != null ? new ResponseEntity<>(roleList, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // send logout URL to client so they can initiate logout
        var issuerUri = registration.getProviderDetails().getIssuerUri();
        var originUrl = request.getHeader(HttpHeaders.ORIGIN);
        Object[] params = {issuerUri, registration.getClientId(), originUrl};
        // Yes! We @ Auth0 should have an end_session_endpoint in our OIDC metadata.
        // It's not included at the time of this writing, but will be coming soon!
        var logoutUrl = MessageFormat.format("{0}v2/logout?client_id={1}&returnTo={2}", params);
        request.getSession().invalidate();
        return ResponseEntity.ok().body(of("logoutUrl", logoutUrl));
    }
}
