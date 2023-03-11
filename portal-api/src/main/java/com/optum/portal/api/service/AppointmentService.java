package com.optum.portal.api.service;

import com.optum.portal.api.model.Appointment;
import com.optum.portal.api.model.AppointmentRequest;
import com.optum.portal.api.model.User;
import com.optum.portal.api.repository.IAppointmentRepository;
import com.optum.portal.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;

    @Autowired
    IUserRepository userRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * save appointment
     * @param appointmentRequest
     * @return
     */
    public Appointment save(AppointmentRequest appointmentRequest) {

        Optional<User> user = userRepository.findById(appointmentRequest.getUserId());
        Optional<User> doctor = userRepository.findById(appointmentRequest.getDoctorId());

        Appointment appointment = new Appointment();
        appointment.setAppointment(LocalDateTime.parse(appointmentRequest.getAppointmentDate(), FORMATTER));
        appointment.setUser(user.get());
        appointment.setDoctor(doctor.get());
        appointment.setReason(appointmentRequest.getReason());
        if(appointment.getAppointmentId() != null) {
            appointment.setUpdatedDate(LocalDate.now());
            appointment.setUpdatedBy("System");
        } else {
            appointment.setCreatedDate(LocalDate.now());
            appointment.setCreatedBy("System");
        }
        return iAppointmentRepository.save(appointment);
    }

    /**
     * save appointment
     * @param appointmentId
     * @return
     */
    public void cancel(Long appointmentId) {
        if(appointmentId != null) {
            iAppointmentRepository.deleteById(appointmentId);
        }
    }

    public List<Appointment> getAppointments(Long userId) {
        if(userId != null) {
            Optional<User> user = userRepository.findById(userId);
            return iAppointmentRepository.findAppointmentsForUser(user.get());
        }
        return null;
    }
}
