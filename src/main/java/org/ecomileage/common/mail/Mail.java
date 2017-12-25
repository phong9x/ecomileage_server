package org.ecomileage.common.mail;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.ecomileage.web.common.utils.ConstantUtils;

public class Mail {

    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void sendMail(String to, String subject) {
        try {
            System.setProperty("mail.mime.charset", "utf8");
            SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
            message.setTo(to);
            message.setText(String.format(simpleMailMessage.getText(), subject));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static boolean sendEmail(String toEmail, String content) {
        try {
            if (toEmail != null) {
                ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
                JavaMailSenderImpl sender = (JavaMailSenderImpl) context.getBean("mailSender");
                System.out.println(sender.getUsername() + "|" + sender.getPassword() + "|" + sender.getHost());
                Mail mm = (Mail) context.getBean("mail");
                mm.sendMail(toEmail, content);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public enum EmailTemplate {
    	VERYFY_EMAIL("velocity/find_password.vm"),
		;
        String template;

        EmailTemplate(String template) {
            this.template = template;
        }

        public String getTemplate() {
            return template;
        }
    }

    public static void sendEmailTemplate(String to, Map<String, Object> params, EmailTemplate template, String subject) {
    	try {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        VelocityEngine velocityEngine = (VelocityEngine) context.getBean("velocityEngine");
        params.put("url", ConstantUtils.getConfig("domain"));
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom("test.tramsvn@gmail.com");
                message.setSubject(params.getOrDefault("subject", subject).toString());

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template.template, "UTF-8", params);

                message.setText(text, true);
            }
        };

        JavaMailSenderImpl sender = (JavaMailSenderImpl) context.getBean("mailSender");
        sender.setDefaultEncoding("UTF-8");
        sender.send(preparator);
        System.out.println("Send email success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    

}
