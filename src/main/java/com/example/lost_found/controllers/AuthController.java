package com.example.lost_found.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.lost_found.domain.Role;
import com.example.lost_found.domain.User;
import com.example.lost_found.domain.UserStatus;
import com.example.lost_found.domain.UserType;
import com.example.lost_found.jwt.JwtUtils;
import com.example.lost_found.payload.LoginRequest;
import com.example.lost_found.payload.ResponseSignIn;
import com.example.lost_found.payload.SignUpRequest;
import com.example.lost_found.services.RoleService;
import com.example.lost_found.services.UserDetailsImplementation;
import com.example.lost_found.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody LoginRequest user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(role -> role.getAuthority())
                                        .collect(Collectors.toList());

        User loggedInUser = userService.findById(userDetails.getId());
        if(loggedInUser.getUserStatus().name().equals("DISABLED")){
            SecurityContextHolder.clearContext();
            return ResponseEntity.badRequest().body(new String("Your account has been disabled"));
        }

        return ResponseEntity.ok(
            new ResponseSignIn(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles)
        );
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<?> registerSimpleUser(@Valid @RequestBody SignUpRequest user){
        System.out.println(user);
        if(userService.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new String("Username exists"));
        }
        if(userService.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new String("Email exists"));
        }

        User newUser = new User(
            user.getUsername(), 
            encoder.encode(user.getPassword()), 
            user.getEmail(), 
            user.getPhoneNumber(), 
            UserStatus.ACTIVE);

        Role role = roleService.findByUserType(UserType.CUSTOMER);
        if(role == null){
            role = roleService.saveNewRole(new Role(UserType.CUSTOMER));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);

        return ResponseEntity.ok(userService.saveUser(newUser));
    }

    @PostMapping("/signup/staff")
    public ResponseEntity<?> registerStaffUser(@Valid @RequestBody SignUpRequest user){
        if(userService.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new String("Username exists"));
        }
        if(userService.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new String("Email exists"));
        }

        User newUser = new User(
            user.getUsername(), 
            encoder.encode(user.getPassword()), 
            user.getEmail(), 
            user.getPhoneNumber(), 
            UserStatus.ACTIVE);

        Role role = roleService.findByUserType(UserType.STAFF);
        if(role == null){
            role = roleService.saveNewRole(new Role(UserType.STAFF));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);

        return ResponseEntity.ok(userService.saveUser(newUser));
    }
    @GetMapping("/disable")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> disableCustomer(@RequestParam int id){
        User user = userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getRoles().contains(new Role(UserType.STAFF))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setUserStatus(UserStatus.DISABLED);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "Successfully logged out";
    }
}
