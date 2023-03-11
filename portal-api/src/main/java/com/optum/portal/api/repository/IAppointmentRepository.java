package com.optum.portal.api.repository;

import com.optum.portal.api.model.Appointment;
import com.optum.portal.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppointmentRepository  extends JpaRepository<Appointment, Long> {

    @Query("Select a from Appointment a WHERE a.user=:user")
    List<Appointment> findAppointmentsForUser(@Param("user") User user);
}
