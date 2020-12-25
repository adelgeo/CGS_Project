package com.adel.service;

import com.adel.model.Student;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    public String getMail() {
        return new Student().getStudentEmail();
    }
}
