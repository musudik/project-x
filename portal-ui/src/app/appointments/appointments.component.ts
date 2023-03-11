import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { AppointmentService } from '../_services/appointment.service';
import { User } from "../model/user.model";

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {
    form: any = {
      selectedDoctor: null,
      reason: null,
      date: null,
      time: null
    };
    public doctors: User[];
    public msg:string;
    public currentUser: User;

    constructor(private authService: AuthService, private appointmentService: AppointmentService, private tokenStorage: TokenStorageService, private formBuilder: FormBuilder) {
      this.doctors = [];
      this.msg = '';
      this.form.selectedDoctor = '';
      this.form.reason = '';
      this.form.date = new Date().toISOString().split('T')[0];
      this.form.time = new Date().toISOString().split('T')[1];
    }

    ngOnInit() {
        this.currentUser = this.tokenStorage.getUser();
        let userId = this.currentUser.id;
        console.log('userId==>', userId);

        this.authService.getDoctors().subscribe(
          data => {
            this.doctors = data.output;
          },
          err => {
            this.doctors = JSON.parse(err.error).message;
          }
        );
    }

    onSubmit(): void {
      const { selectedDoctor, reason, date, time } = this.form;
      let userId = this.currentUser.id;
      let datTime = date+" "+time+":00";
      console.log('Appointment on==>', datTime);
      console.log('userId==>', userId);
      console.log('selectedDoctor==>', this.form.selectedDoctor);
      console.log('reason==>', this.form.reason);
      this.appointmentService.create(userId, selectedDoctor, reason, datTime).subscribe(
        data => {
          console.log(data);
          this.msg = data.message;
        },
        err => {
          this.msg = err.error.message;
        }
      );
    }
}
