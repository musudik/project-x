package com.optum.portal.api.controller;

import com.optum.portal.api.model.Appointment;
import com.optum.portal.api.model.AppointmentRequest;
import com.optum.portal.api.model.Result;
import com.optum.portal.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    /**
     *
     * @param appointmentProgress
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Result> createAppointment(@RequestBody AppointmentRequest appointmentProgress) {
        Result result = new Result();
        try {
            Appointment appointment = appointmentService.save(appointmentProgress);
            result.setResult(Result.SUCCESS);
            result.setOutput(appointment);
            result.setMessage("Appointment created successful for " + appointment.getAppointmentId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Appointment creation failed, Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param appointmentId
     * @return
     */
    @GetMapping("/cancel/{appointmentId}")
    public ResponseEntity<String> cancel(@PathVariable("appointmentId") String appointmentId) {
        try {
            appointmentService.cancel(Long.valueOf(appointmentId));
            return new ResponseEntity<>("Successfully cancelled the appointment: "+appointmentId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to cancel the appointment: "+appointmentId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAppointments/{userId}")
    public ResponseEntity<Result> getAppointments(@PathVariable("userId") String userId) {

        Result result = new Result();
        try {
            List<Appointment> appointments = appointmentService.getAppointments(Long.valueOf(userId));
            result.setResult(Result.SUCCESS);
            result.setMessage("No.of appointments found are: " + appointments.size());
            result.setOutput(appointments);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("No appointments found");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
