package com.optum.portal.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPOINTMENT", uniqueConstraints={@UniqueConstraint(columnNames = {"appointment_id", "appointment"})})
@SequenceGenerator(name="seq_appointment", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public Appointment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_appointment")
    @Column(name = "appointment_id", nullable = false)
    Long appointmentId;

    @OneToOne
    @JoinColumn(name="user")
    private User user;

    @OneToOne
    @JoinColumn(name="doctor")
    private User doctor;

    @Column(name = "appointment", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointment;

    @Column(name = "reason")
    private String reason;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getAppointment() {
        return appointment;
    }

    public void setAppointment(LocalDateTime appointment) {
        this.appointment = appointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
