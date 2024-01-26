
package com.harshproject.util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.harshproject.schedulercomponent.SchedulerComponent;

@Aspect
@Component
public class DepartmentEmailAspect {

    @Autowired
    private MailSender mailSender;
    
//    @Autowired
//    private SchedulerComponent schedulerComponent;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmailAspect.class);
    
    @Before("execution(* com.harshproject.util.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery10sec())")
    public void sendEmailForTask0() {
        sendEmail("fake-email@example.com", "Scheduled Task Confirmation 0",
                "The scheduled task has been executed successfully: Task Executed Every 10 sec");
    }

    @Before("execution(* com.harshproject.util.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery5Seconds())")
    public void sendEmailForTask1() {
        sendEmail("fake-email@example.com", "Scheduled Task Confirmation 1",
                "The scheduled task has been executed successfully: Task Executed Every 5 min");
    }

    @Before("execution(* com.harshproject.util.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery30Minutes())")
    public void sendEmailForTask2() {
        sendEmail("fake-email@example.com", "Scheduled Task Confirmation 2",
                "The scheduled task has been executed successfully: Task Executed Every 30 Minutes");
    }

    @Before("execution(* com.harshproject.util.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery1hr())")
    public void sendEmailForTask3() {
        sendEmail("fake-email@example.com", "Scheduled Task Confirmation 3",
                "The scheduled task has been executed successfully: Task Executed Every 1 hour");
    }

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
	                logger.info("HTTP 204 OK");
	                sendConfirmationEmail(methodName, "HTTP 204 OK");
	                break;
            	
                case 200:
                    logger.info("HTTP 200 OK");
                    sendConfirmationEmail(methodName, "HTTP 200 OK");
                    break;
                case 201:
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



