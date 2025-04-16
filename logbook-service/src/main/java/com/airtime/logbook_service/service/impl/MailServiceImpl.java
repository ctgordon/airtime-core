package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.model.Ato;
import com.airtime.logbook_service.persistence.model.Flight;
import com.airtime.logbook_service.persistence.model.Goal;
import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.service.AtoService;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.service.MailService;
import com.airtime.logbook_service.web.dto.FlightDTO;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Service("mailService")
public class MailServiceImpl implements MailService {
    private final FlightService flightService;
    private final AtoService atoService;
    private final JavaMailSender javaMailSender;

    @Value("${app.email.send}")
    private boolean sendEmail;

    @Value("${app.email.name}")
    private String name;

    @Value("${app.email.sender}")
    private String senderEmail;

    public MailServiceImpl(FlightService flightService, AtoService atoService, JavaMailSender javaMailSender) {
        this.flightService = flightService;
        this.atoService = atoService;
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

    /*@Override
    public void sendDailyUpdate() {
        final String subject = "Airtime: Daily flight log updates";

        List<Subscriber> subscribers = subscriberService.subscriberList();

        if (subscribers != null && !subscribers.isEmpty()) {
            subscribers.forEach(subscriber -> {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setTo(subscriber.getPerson().getAppEmailAddress());
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setFrom(senderEmail);
                String content = "";
                Person person = subscriber.getPerson();
                if (subscriber.isDailyEmail() && person != null && person.getAppEmailAddress() != null) {

                    List<Flight> flightList = flightService.getAllFlightsByOwner(person);

                    if (flightList != null && !flightList.isEmpty()) {
                        FlightSummaryDTO flightSummaryDTO = flightService.getFlightSummary(flightList);

                        if (flightSummaryDTO != null) {
                            content += "<!DOCTYPE html>";
                            content += "<html lang=\"en\">";
                            content += "<head>";
                            content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
                            content += "<meta name=\"viewport\" content=\"width=device-width\">";
                            content += "<link rel=\"stylesheet\" \n" +
                                    "href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">";
                            content += "<title></title>";
                            content += "<style></style>";
                            content += "</head>";

                            content += "<div id=\"email\" style=\"width:600px;margin: auto;background:white;\">";

                            content += browserLink();
                            content += emailHeader();
                            content += emailIntro(person);
                            content += flightTotals(flightSummaryDTO);
                            content += lastFlight(flightSummaryDTO);
                            content += timeSinceAto();
                            content += "<p></p>";

                            content += emailFooter();

                            content += "</div>";

                            content += "</body>";
                        }
                    }
                }
                simpleMailMessage.setText(content);
                sendEmail(simpleMailMessage);
            });
        }
    }*/

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

    private String emailIntro(Profile profile) {
        return openTable() +
                "     <tr>\n" +
                "       <td style=\"padding: 30px 0;\">\n" +
                "        <h2 style=\"font-size: 28px; margin:0 0 20px 0; font-family:Arial;\"> Hello, " + profile.getForename() + ".</h2>\n" +
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

    private String flightTotals(FlightSummaryDTO flightSummaryDTO) {
        String content = "";
        content += "<div style=\"padding: 30px 0;\">";
        content += "<h3 style=\"font-size: 28px; margin:0 0 20px 0; font-family:Arial;\"> Flight totals</h3>";
        content += openTable();
        content += "<tbody>";
        content += "<tr>";
        content += "<td><strong>Total hours<strong></td><td>" + flightSummaryDTO.getTotalHours() + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>Total hours PIC<strong></td><td>" + flightSummaryDTO.getTotalHoursPIC() + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>Total hours dual<strong></td><td>" + flightSummaryDTO.getTotalHoursDual() + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>Total flights<strong></td><td>" + flightSummaryDTO.getTotalFlights() + "</td>";
        content += "</tr>";
        content += "</tbody>";
        content += "</table>";
        content += "</div>";

        return content;
    }

    private String lastFlight(FlightSummaryDTO flightSummaryDTO) {
        String content = "";
        content += "<div style=\"padding: 30px 0;\">";
        content += "<h3 style=\"font-size: 28px; margin:0 0 20px 0; font-family:Arial;\"> Last flight</h3>";
        content += flightDetails(flightSummaryDTO.getLastFlight());
        content += "</div>";
        return content;
    }

    private String flightDetails(FlightDTO flightDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss a z");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String content = "";
        content += openTable();
        content += "<tbody>";

        content += "<tr>";
        content += "<td><strong>Organization<strong></td><td>" + flightDTO.getAto().getName() + "</td>";
        content += "</tr>";

        content += "<tr>";
        content += "<td><strong>Flight time<strong></td><td>" + flightDTO.getFlightTimeMinutes() + " minutes</td>";
        content += "</tr>";

        if (flightDTO.getDepartureDatetime() != null) {
            content += "<tr>";
            String formatted = simpleDateFormat.format(flightDTO.getDepartureDatetime());
            content += "<td><strong>Departed<strong></td><td>" + formatted + "</td>";
            content += "</tr>";
        }

        if (flightDTO.getArrivalDatetime() != null) {
            Timestamp arrivalDatetime = flightDTO.getArrivalDatetime();
            content += "<tr>";
            String formatted = simpleDateFormat.format(arrivalDatetime);
            content += "<td><strong>Arrived<strong></td><td>" + formatted + "</td>";
            content += "</tr>";

            Date today = new Date();
            Date lastFlightDate = new Date(arrivalDatetime.getTime());

            String difference = "";
            int remainingDays = 0;

            long differenceInTime
                    = today.getTime() - lastFlightDate.getTime();

            long differenceInYears
                    = (differenceInTime
                    / (1000L * 60 * 60 * 24 * 365));

            if (differenceInYears < 1) {
                long differenceInDays
                        = (differenceInTime
                        / (1000 * 60 * 60 * 24))
                        % 365;
                difference = differenceInDays + " days";
                remainingDays = (int) (flightDTO.getAto().getClubCurrency() - differenceInDays);
            } else {
                difference = differenceInYears + " years";
            }

            content += "<tr>";
            content += "<td><strong>Elapsed time<strong></td><td>" + difference + "</td>";
            content += "</tr>";

            content += "<tr>";
            content += "<td><strong>Club currency<strong></td><td>" + flightDTO.getAto().getClubCurrency() + " days</td>";
            content += "</tr>";

            content += "<tr>";
            content += "<td><strong>Currency remaining<strong></td><td>" + remainingDays + " days</td>";
            content += "</tr>";
        }

        content += "<tr>";
        content += "<td><strong>Aircraft<strong></td><td>" + flightDTO.getAircraft().getTailNumber() + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>PIC<strong></td><td>" + flightDTO.getPilotInCommand().getKnownAs() + "</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>From<strong></td><td>" + flightDTO.getDepartureAirport().getAirportName() + " (" + flightDTO.getDepartureAirport().getAirportCode() + ")</td>";
        content += "</tr>";
        content += "<tr>";
        content += "<td><strong>To<strong></td><td>" + flightDTO.getArrivalAirport().getAirportName() + " (" + flightDTO.getArrivalAirport().getAirportCode() + ")</td>";
        content += "</tr>";

        content += "<tr>";
        content += "<td><strong>Remarks<strong></td><td>" + flightDTO.getRemarks() + "</td>";
        content += "</tr>";
        content += "</tbody>";
        content += "</table>";

        return content;
    }

    /*private String memberships(List<Membership> memberships) {
        StringBuilder content = new StringBuilder();
        content.append("<div style=\"padding: 30px 0;\">");
        content.append("<h3 style=\"font-size: 28px; margin:0 0 20px 0; font-family:Arial;\"> Memberships (").append(memberships.size()).append(")</h3>");


        for (Membership membership : memberships) {

            content.append(openTable());
            content.append("<tbody>");
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date startDate = new Date(membership.getMembershipStart().getTime());
            Date endDate = new Date(membership.getMembershipEnd().getTime());
            String start = dateFormat.format(startDate);
            String end = dateFormat.format(endDate);
            content.append("<tr>").append("<td><strong>Membership body</strong></td>").append("<td>").append(membership.getMembershipBody().getBody()).append("</td>").append("</tr>");
            content.append("<tr>").append("<td><strong>Description</strong></td>").append("<td>").append(membership.getDescription()).append("</td>").append("</tr>");
            content.append("<tr>").append("<td><strong>Starts</strong></td>").append("<td>").append(start).append("</td>").append("</tr>");
            content.append("<tr>").append("<td><strong>Ends</strong></td>").append("<td>").append(end).append("</td>").append("</tr>");
            content.append("<tr>").append("<td><strong>Status</strong></td>").append("<td>").append(status(startDate, endDate)).append("</td>").append("</tr>");
            content.append("</tbody>");
            content.append("</table>");

        }
        content.append("</div>");
        return content.toString();
    }*/

    private String status(Date startDate, Date endDate) {
        String status = "";
        Date now = new Date();

        if (now.after(endDate)) {
            status = "Expired";
        }
        if (now.before(startDate)) {
            status = "Not yet started";
        }
        if (now.after(startDate) && now.before(endDate)) {
            status = "Active";
        }

        return status;
    }

    private String openTable() {
        return "<table role=\"presentation\" border=\"0\" cellspacing=\"0\" width=\"100%\">";
    }
}
