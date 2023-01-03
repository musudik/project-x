import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Program, ProgramAdapter } from "../model/program.model";
import { map } from "rxjs/operators";

const API_URL = 'http://localhost:8888/program/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
  constructor(private http: HttpClient, private adapter: ProgramAdapter) { }

   //get all programs
   getPrograms(): Observable<any> {
     return this.http.get(API_URL, { });
   }

   //register user for programs based on userId and programIds
   register(userId: string, programIds: string[]): Observable<any> {
     return this.http.post(API_URL + 'register', {
       userId,
       programIds
     }, httpOptions);
   }

   //deregister user for programs based on userId and programIds
   deregister(userId: string, programIds: string[]): Observable<any> {
     return this.http.post(API_URL + 'deregister', {
      userId,
      programIds
     }, httpOptions);
   }

   //get all programs
   getProgramById(programId: string): Observable<any> {
     return this.http.get(API_URL + 'getProgramById/' + programId, { });
   }

   getUserActivities(program:string, user:string): Observable<any> {
     return this.http.post(API_URL + 'getUserActivities', {
      program,
      user
     }, httpOptions);
   }

   createActivity(program:string, activities:string[], user:string, complete:string): Observable<any> {
      return this.http.post(API_URL + 'createActivity', {
       program,
       activities,
       user,
       complete
      }, httpOptions);
    }
}
