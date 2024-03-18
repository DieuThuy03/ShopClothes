package com.example.shopclothes.utils;

import com.example.shopclothes.entity.Account;
import com.example.shopclothes.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class EmailSend {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(Account account) {
        // Code gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("beesport.fpoly@gmail.com");
        String subject = "[Q_H Store] Chào mừng bạn đến với Q_HStore, đây là thông tin tài khoản của bạn";
        String content = " Họ Và Tên: " + account.getFullName()+ "\n Số Điện Thoại: " + account.getPhoneNumber()+ "\n Email: " + account.getEmail()+ "\n Mật khẩu: " + account.getPassword();
        message.setTo(account.getEmail());
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);

    }

    public String randomPasswords() {
        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(kyTu.length());
            password.append(kyTu.charAt(index));
        }
        return password.toString();
    }
}
