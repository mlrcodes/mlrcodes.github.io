package com.miguel.raffles.Email;

import com.miguel.raffles.Kafka.Order.CustomerDTO;
import com.miguel.raffles.Kafka.Order.OrderConfirmation;
import com.miguel.raffles.Kafka.Order.PaymentData;
import com.miguel.raffles.Kafka.Payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static com.miguel.raffles.Email.EmailTemplates.ORDER_CONFIRMATION;
import static com.miguel.raffles.Email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentConfirmationEmail(PaymentConfirmation paymentConfirmation) throws MessagingException {
        Map<String, Object> variables = createPaymentConfirmationVariables(paymentConfirmation);
        sendEmail(paymentConfirmation.customerEmail(),
                PAYMENT_CONFIRMATION.getSubject(),
                PAYMENT_CONFIRMATION.getTemplate(),
                variables
        );
    }

    @Async
    public void sendOrderConfirmationEmail(OrderConfirmation orderConfirmation) throws MessagingException {
        Map<String, Object> variables = createOrderConfirmationVariables(orderConfirmation);
        sendEmail(orderConfirmation.customer().email(),
                ORDER_CONFIRMATION.getSubject(),
                ORDER_CONFIRMATION.getTemplate(),
                variables
        );
    }

    private Map<String, Object> createPaymentConfirmationVariables(PaymentConfirmation paymentConfirmation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderReference", paymentConfirmation.orderReference());
        variables.put("paymentMethod", paymentConfirmation.paymentMethod());
        variables.put("total", paymentConfirmation.total());
        variables.put("customerName", paymentConfirmation.customerName());
        variables.put("customerEmail", paymentConfirmation.customerEmail());
        return variables;
    }

    private Map<String, Object> createOrderConfirmationVariables(OrderConfirmation orderConfirmation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderReference", orderConfirmation.orderReference());

        CustomerDTO customer = orderConfirmation.customer();
        variables.put("customerName", customer.name());
        variables.put("email", customer.email());
        variables.put("phoneNumber", customer.phoneNumber());

        PaymentData paymentData = orderConfirmation.paymentData();
        variables.put("total", paymentData.total());
        variables.put("paymentMethod", paymentData.paymentMethod());

        variables.put("tickets", orderConfirmation.tickets());

        return variables;
    }

    private void sendEmail(String to,
                           String subject,
                           String templateName,
                           Map<String, Object> variables
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        Context context = new Context();
        context.setVariables(variables);
        String htmlTemplate = templateEngine.process(templateName, context);

        messageHelper.setSubject(subject);
        messageHelper.setText(htmlTemplate, true);
        messageHelper.setTo(to);
        messageHelper.setFrom("${spring.mail.username}");

        mailSender.send(mimeMessage);
    }

}
