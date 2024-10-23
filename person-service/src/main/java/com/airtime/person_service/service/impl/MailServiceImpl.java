package com.airtime.person_service.service.impl;

import com.airtime.person_service.persistence.model.Person;
import com.airtime.person_service.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service("mailService")
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Value("${app.email.send}")
    private boolean sendEmail;

    @Value("${app.email.name}")
    private String name;

    @Value("${app.email.sender}")
    private String senderEmail;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private boolean sendEmail(SimpleMailMessage simpleMailMessage) {
        boolean sent = false;

        if (sendEmail) {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, false, StandardCharsets.UTF_8.name());
                helper.setTo(Objects.requireNonNull(simpleMailMessage.getTo()));
                helper.setSubject(Objects.requireNonNull(simpleMailMessage.getSubject()));
                helper.setText(Objects.requireNonNull(simpleMailMessage.getText()), true);
                helper.setFrom(Objects.requireNonNull(simpleMailMessage.getFrom()));
                javaMailSender.send(message);
                sent = true;
            } catch (MessagingException | MailException ex) {
                logger.error("Exception: " + ex.getMessage());
            }
        } else {
            logger.error("Emailing is not enabled for this app.");
        }

        return sent;
    }

    @Override
    public boolean sendTestEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Airtime: Test email");
        simpleMailMessage.setTo("ctgordon@outlook.com");
        simpleMailMessage.setText("Hello world");
        simpleMailMessage.setFrom(senderEmail);
        return this.sendEmail(simpleMailMessage);
    }

    private String browserLink() {
        return openTable() +
                "    <tr>\n" +
                "      <td>\n" +
                "        <a href=\"#\" style=\"font-size: 9px; text-transform:uppercase; letter-spacing: 1px; color: #99ACC2;  font-family:Arial;\">View in Browser</a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>";
    }

    private String emailHeader() {
        return "<!-- Header --> \n" +
                openTable() +
                "  <tr>\n" +
                "    <td bgcolor=\"#00A4BD\" align=\"center\" style=\"color: white;\">\n" +
                " <i class=\"fa-solid fa-plane-departure\"></i>" +
                "      <h1 style=\"font-size: 52px; margin:20px 0 20px 0; font-family:Arial;\"> Airtime logbook</h1>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>";
    }

    private String emailIntro(Person person) {
        return openTable() +
                "     <tr>\n" +
                "       <td style=\"padding: 30px 0;\">\n" +
                "        <h2 style=\"font-size: 28px; margin:0 0 20px 0; font-family:Arial;\"> Hello, " + person.getName() + ".</h2>\n" +
                "         <p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial\">Ut eget semper libero. Vestibulum non maximus nisl, ut iaculis ante. Nunc arcu elit, cursus eget urna et, tempus aliquam eros. Ut eget semper libero. Vestibulum non maximus nisl, ut iaculis ante. Nunc arcu elit, cursus eget urna et, tempus aliquam eros.</p>\n" +
                "           <p style=\"margin:0;font-size:16px;line-height:24px;font-family:Arial;\"><a href=\"#\" style=\"color:#FF7A59;text-decoration:underline;\">Learn more</a></p>\n" +
                "         </td> \n" +
                "    </tr>\n" +
                "  </table>";
    }

    private String emailFooter() {
        return openTable() +
                "      <tr>\n" +
                "          <td bgcolor=\"#F5F8FA\" style=\"padding: 30px 0; text-align: center;\">\n" +
                "            <p style=\"margin:0 0 12px 0; padding: 0 30px; font-size:16px; line-height:24px; color: #99ACC2; font-family:Arial\"> Made by Artificial Horizon |  \n" +
                "            <a href=\"#\" style=\"font-size: 9px; text-transform:uppercase; letter-spacing: 1px; color: #99ACC2;  font-family:Arial;\"> Unsubscribe </a></p> " +
                "          </td>\n" +
                "          </tr>\n" +
                "      </table> ";
    }

    private String openTable() {
        return "<table role=\"presentation\" border=\"0\" cellspacing=\"0\" width=\"100%\">";
    }
}
