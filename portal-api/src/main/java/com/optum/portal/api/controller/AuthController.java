package com.optum.portal.api.controller;

import com.optum.portal.api.model.MailObject;
import com.optum.portal.api.model.Result;
import com.optum.portal.api.model.User;
import com.optum.portal.api.service.AuthService;
import com.optum.portal.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    public EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody User user) {
        Result result = new Result();
        try {
            User loggedInUser = authService.login(user);
            loggedInUser.setAccessToken(UUID.randomUUID().toString());
            result.setResult(Result.SUCCESS);
            result.setMessage("Login Successful with user: " + loggedInUser.getUsername());
            result.setOutput(loggedInUser);
            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Login Failed with user: " + user.getUsername()+ "" +
                    ", Reason: "+e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<Result> signUp(@RequestBody User user) {
        Result result = new Result();
        try {
            User newUser = new User(user.getFirstName(), user.getLastName(),
                    user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
            User signedUpUser = authService.save(newUser);
            sendEmail(signedUpUser);
            result.setResult(Result.SUCCESS);
            result.setMessage("Signup Successful with user: " + signedUpUser.getUsername());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            result.setResult(Result.FAILED);
            result.setMessage("Signup Failed with username: " + user.getUsername()+ "." +
                    " Reason: The username given already exists, please try another username");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Signup Failed with username: " + user.getUsername()+ "." +
                    " Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getUserById/{id}")
    public ResponseEntity<Result> getUserById(@PathVariable("id") Long id) {

        Result result = new Result();
        try {
            User user = authService.getUserById(id);
            result.setResult(Result.SUCCESS);
            result.setMessage("User found with id: " + id);
            result.setOutput(user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("User not found with id: " + id);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String createMail(Model model,
                             @ModelAttribute("mailObject") @Valid MailObject mailObject,
                             Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());

        return "emails";
    }

    private void sendEmail(User user) {
        emailService.sendSimpleMessage(user.getEmail(),
                "Signup Successful", "Hello, User Signup successful   -Thanks!");
    }
}
