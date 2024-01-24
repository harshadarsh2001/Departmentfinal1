
package com.harshproject.util;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.mail.MailSender;
//
//@Aspect
//@Component
//public class DepartmentEmailAspect {
//
//    @Autowired
//    private MailSender mailSender;
//
//    // Define pointcut expressions for the methods you want to intercept in the DepartmentController
////    @Before("execution(* com.harshproject.controller.DepartmentController.*(..))")
////    public void sendEmailBeforeMethodExecution(JoinPoint joinPoint) {
////        String methodName = joinPoint.getSignature().getName();
////        String to = "fake-email@example.com";
////        String subject = "Department Method Execution Started: " + methodName;
////        String text = "The method " + methodName + " in the DepartmentController has started its execution.";
////        sendEmail(to, subject, text);
////    }
//
//    @After("execution(* com.harshproject.controller.DepartmentController.*(..))")
//    public void sendEmailAfterMethodExecution(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String to = "fake-email@example.com";
//        String subject = "Department Method Execution Completed: " + methodName;
//        String text = "The method " + methodName + " in the DepartmentController has completed its execution.";
//        sendEmail(to, subject, text);
//    }
//    
//    @Scheduled(cron = "*/10 * * * * *") 
//    public void sendScheduledTaskConfirmationEmailEvery10sec() {
//    	System.out.println("Displaying message every 10 sec: Scheduled Task 0");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 0";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//    
//    @Scheduled(cron = "0 */5 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
//    	System.out.println("Displaying message every 5 minutes: Scheduled Task 1");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 1";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 5 Seconds";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 */30 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
//    	System.out.println("Displaying message every 30 minutes: Scheduled Task 2");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 2";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//    
//    @Scheduled(cron = "0 0 */1 * * *")
//    public void sendScheduledTaskConfirmationEmailEvery1hr() {
//    	System.out.println("Displaying message every 1 hr: Scheduled Task 3");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 3";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    private void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//    
//}

//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.mail.MailSender;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class DepartmentEmailAspect {
//
//    @Autowired
//    private MailSender mailSender;
//
//    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmailAspect.class);
//
//    @AfterReturning(pointcut = "execution(* com.harshproject.controller.DepartmentController.*(..))", returning = "result")
//    public void handleHttpCodes(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("Handling HTTP codes for method: {}", methodName);
//
//        if (result instanceof ResponseEntity) {
//            int statusCodeValue = ((ResponseEntity<?>) result).getStatusCodeValue();
//
//            // Add your custom logic based on HTTP status codes
//            switch (statusCodeValue) {
//                case 200:
//                    // Handle 200 OK
//                    logger.info("HTTP 200 OK");
//                    sendConfirmationEmail("HTTP 200 OK");
//                    break;
//                case 201:
//                    // Handle 201 Created
//                    logger.info("HTTP 201 Created");
//                    sendConfirmationEmail("HTTP 201 Created");
//                    break;
//                case 404:
//                    // Handle 404 Not Found
//                    logger.info("HTTP 404 Not Found");
//                    sendConfirmationEmail("HTTP 404 Not Found");
//                    break;
//                // Add more cases as needed
//                default:
//                    // Handle other HTTP status codes
//                    logger.info("Unhandled HTTP status code: {}", statusCodeValue);
//            }
//        }
//    }
//
//    @Scheduled(cron = "*/10 * * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery10sec() {
//        System.out.println("Displaying message every 10 sec: Scheduled Task 0");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 0";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 */5 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
//        System.out.println("Displaying message every 5 minutes: Scheduled Task 1");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 1";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 5 Seconds";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 */30 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
//        System.out.println("Displaying message every 30 minutes: Scheduled Task 2");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 2";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 0 */1 * * *")
//    public void sendScheduledTaskConfirmationEmailEvery1hr() {
//        System.out.println("Displaying message every 1 hr: Scheduled Task 3");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 3";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    private void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//
//    private void sendConfirmationEmail(String message) {
//        String to = "fake-email@example.com";
//        String subject = "HTTP Code Confirmation";
//        sendEmail(to, subject, message);
//    }
//}

//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.MailSender;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class DepartmentEmailAspect {
//
//    @Autowired
//    private MailSender mailSender;
//
//    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmailAspect.class);
//
//    @AfterReturning(pointcut = "execution(* com.harshproject.controller.DepartmentController.*(..))", returning = "result")
//    public void handleHttpCodes(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("Handling HTTP codes for method: {}", methodName);
//
//        if (result instanceof ResponseEntity) {
//            int statusCodeValue = ((ResponseEntity<?>) result).getStatusCodeValue();
//
//            // Add your custom logic based on HTTP status codes
//            switch (statusCodeValue) {
//                case 200:
//                    // Handle 200 OK
//                    logger.info("HTTP 200 OK");
//                    sendConfirmationEmail(methodName, "HTTP 200 OK");
//                    break;
//                case 201:
//                    // Handle 201 Created
//                    logger.info("HTTP 201 Created");
//                    sendConfirmationEmail(methodName, "HTTP 201 Created");
//                    break;
//                case 404:
//                    // Handle 404 Not Found
//                    logger.info("HTTP 404 Not Found");
//                    sendConfirmationEmail(methodName, "HTTP 404 Not Found");
//                    break;
//                // Add more cases as needed
//                default:
//                    // Handle other HTTP status codes
//                    logger.info("Unhandled HTTP status code: {}", statusCodeValue);
//                    sendErrorEmail(methodName, "Unhandled HTTP status code: " + statusCodeValue);
//            }
//        }
//    }
//
//    @Scheduled(cron = "*/10 * * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery10sec() {
//        logger.info("Displaying message every 10 sec: Scheduled Task 0");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 0";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 */5 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
//        logger.info("Displaying message every 5 minutes: Scheduled Task 1");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 1";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 5 Seconds";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 */30 * * * *")
//    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
//        logger.info("Displaying message every 30 minutes: Scheduled Task 2");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 2";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 0 */1 * * *")
//    public void sendScheduledTaskConfirmationEmailEvery1hr() {
//        logger.info("Displaying message every 1 hr: Scheduled Task 3");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 3";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
//    }
//
//    private void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//
//    private void sendConfirmationEmail(String methodName, String message) {
//        String to = "fake-email@example.com";
//        String subject = "Method Execution Confirmation - " + methodName;
//        sendEmail(to, subject, message);
//    }
//
//    private void sendErrorEmail(String methodName, String errorMessage) {
//        String to = "fake-email@example.com";
//        String subject = "Error in Method Execution - " + methodName;
//        sendEmail(to, subject, errorMessage);
//    }
//}

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DepartmentEmailAspect {

    @Autowired
    private MailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmailAspect.class);

    @AfterReturning(pointcut = "execution(* com.harshproject.controller.DepartmentController.*(..))", returning = "result")
    public void handleHttpCodes(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Handling HTTP codes for method: {}", methodName);

        if (result instanceof ResponseEntity) {
            int statusCodeValue = ((ResponseEntity<?>) result).getStatusCodeValue();

            // Add your custom logic based on HTTP status codes
            switch (statusCodeValue) {
	            case 403:
	                
	                logger.info("HTTP 403 Not Found");
	                sendErrorEmail(methodName, "HTTP 403 Not Found");
                break;
	            case 204:
	                // Handle 200 OK
	                logger.info("HTTP 204 OK");
	                sendConfirmationEmail(methodName, "HTTP 204 OK");
	                break;
            	
                case 200:
                    // Handle 200 OK
                    logger.info("HTTP 200 OK");
                    sendConfirmationEmail(methodName, "HTTP 200 OK");
                    break;
                case 201:
                    // Handle 201 Created
                    logger.info("HTTP 201 Created");
                    sendConfirmationEmail(methodName, "HTTP 201 Created");
                    break;
                case 404:
                    logger.info("HTTP 404 Not Found");
                    sendErrorEmail(methodName, "HTTP 404 Not Found");
                    break;
                
                default:
                    logger.info("Unhandled HTTP status code: {}", statusCodeValue);
                    sendErrorEmail(methodName, "Unhandled HTTP status code: " + statusCodeValue);
            }
        } else {
            logger.warn("Unexpected result type: {}", result.getClass().getName());
            sendErrorEmail(methodName, "Unexpected result type: " + result.getClass().getName());
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendScheduledTaskConfirmationEmailEvery10sec() {
        logger.info("Displaying message every 10 sec: Scheduled Task 0");
        String to = "fake-email@example.com";
        String subject = "Scheduled Task Confirmation 0";
        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
        logger.info("Displaying message every 5 minutes: Scheduled Task 1");
        String to = "fake-email@example.com";
        String subject = "Scheduled Task Confirmation 1";
        String text = "The scheduled task has been executed successfully: Task Executed Every 5 Seconds";
        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
        logger.info("Displaying message every 30 minutes: Scheduled Task 2");
        String to = "fake-email@example.com";
        String subject = "Scheduled Task Confirmation 2";
        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void sendScheduledTaskConfirmationEmailEvery1hr() {
        logger.info("Displaying message every 1 hr: Scheduled Task 3");
        String to = "fake-email@example.com";
        String subject = "Scheduled Task Confirmation 3";
        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
        sendEmail(to, subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    private void sendConfirmationEmail(String methodName, String message) {
        String to = "fake-email@example.com";
        String subject = "Method Execution Confirmation - " + methodName;
        sendEmail(to, subject, message);
    }

    private void sendErrorEmail(String methodName, String errorMessage) {
        String to = "fake-email@example.com";
        String subject = "Error in Method Execution - " + methodName;
        sendEmail(to, subject, errorMessage);
    }
}



