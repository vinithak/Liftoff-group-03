package org.launchcode.tutorconnector.controllers;
import org.launchcode.tutorconnector.models.Student;
import org.launchcode.tutorconnector.models.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.launchcode.tutorconnector.service.DailyCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequestMapping("/tutor/meetings")
public class TutorMeetingController {

    private final DailyCoService dailyCoService;

    public TutorMeetingController(DailyCoService dailyCoService) {
        this.dailyCoService = dailyCoService;
    }

    @GetMapping("/create-meeting")
    public String showCreateMeetingForm() {
        return "createMeeting";
    }

    @PostMapping("/create-meeting")
    public String processCreateMeetingForm(@RequestParam("roomName") String roomName, @RequestParam("studentEmail") String studentEmail, @RequestParam("subject") Subject subject, Model model) {
        Map<String, Object> meetingDetails = dailyCoService.createMeeting(roomName);
        model.addAttribute("meetingDetails", meetingDetails);
        model.addAttribute("studentEmail", studentEmail);
        model.addAttribute("subject", subject);
        return "meetingDetails";
    }

    @GetMapping("/join")
    public String joinMeeting(@RequestParam String roomName, Model model) {
        Map<String, Object> meetingDetails = dailyCoService.getMeetingDetails(roomName);

        if (!meetingDetails.containsKey("error")) {
            model.addAttribute("meetingUrl", meetingDetails.get("url")); // URL for the video call
        } else {
            model.addAttribute("error", "Unable to join the meeting");
        }

        return "joinMeeting";
    }
}
