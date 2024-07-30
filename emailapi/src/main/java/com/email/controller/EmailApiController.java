package com.email.controller;

import com.email.model.EmailModel;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailApiController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendemail")
    public String sendEmail() {
        try {
            emailService.sendSimpleEmail("tsage7399@gmail.com", "Test", "Test Mail");
            return "Email sent successfully to";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailModel emailModel){
        try{
            this.emailService.sendSimpleEmail(emailModel.getTo(), emailModel.getSubject(), emailModel.getMessage());
            return ResponseEntity.ok(new EmailResponse("Email is successfully..!!"));
        }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent"));
        }
    }
}
