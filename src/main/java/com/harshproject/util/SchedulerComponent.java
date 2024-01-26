package com.harshproject.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerComponent {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerComponent.class);

//    private final MailSender mailSender;
//
//    public SchedulerComponent(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendScheduledTaskConfirmationEmailEvery10sec() {
        logger.info("Displaying message every 10 sec: Scheduled Task 0");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 0";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
        logger.info("Displaying message every 5 minutes: Scheduled Task 1");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 1";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 5 Seconds";
//        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
        logger.info("Displaying message every 30 minutes: Scheduled Task 2");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 2";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void sendScheduledTaskConfirmationEmailEvery1hr() {
        logger.info("Displaying message every 1 hr: Scheduled Task 3");
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Task Confirmation 3";
//        String text = "The scheduled task has been executed successfully: Task Executed Every 30 Minutes";
//        sendEmail(to, subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
    }
}
