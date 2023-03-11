import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment, AppointmentAdapter } from "../model/appointment.model";
import { map } from "rxjs/operators";

const API_URL = 'http://localhost:8888/appointment/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  constructor(private http: HttpClient, private adapter: AppointmentAdapter) { }

   //get all appointments
   getAppointments(): Observable<any> {
     return this.http.get(API_URL, { });
   }

   //create appointment based on appointment request
   create(userId: string, doctorId: string, reason: string, appointmentDate: string): Observable<any> {
     return this.http.post(API_URL + 'create', {
       userId,
       doctorId,
       reason,
       appointmentDate
     }, httpOptions);
   }

   //cancel appointment based on appointmentId
   cancel(appointmentId: string): Observable<any> {
     return this.http.post(API_URL + 'cancel/'+appointmentId, {
      appointmentId
     }, httpOptions);
   }

   //get user appointments
   getUserAppointments(appointment:string, user:string): Observable<any> {
     return this.http.post(API_URL + 'getAppointments', {
      appointment,
      user
     }, httpOptions);
   }
}
