package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.enums.EmailTemplateName;
import dev.abraham.ecommerce.model.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String to, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("abraham@gmail.com");
        messageHelper.setTo(to);
        final String templateName= EmailTemplateName.PAYMENT_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplateName.PAYMENT_CONFIRMATION.getSubject());
        try{
            String htmlTemplate=templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(to);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MessagingException(e.toString());
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String to, String customerName, String orderReference,
                                           List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("abraham@gmail.com");
        messageHelper.setTo(to);
        BigDecimal amount=new BigDecimal("0");
        for (Product product : products) {
            amount = amount.add(new BigDecimal(product.quantity()).multiply(product.price()));
        }
        final String templateName= EmailTemplateName.ORDER_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplateName.ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate=templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(to);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MessagingException(e.toString());
        }
    }
}