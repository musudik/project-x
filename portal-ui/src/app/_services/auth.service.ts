import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User, UserAdapter } from "../model/user.model";
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8888/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient, private adapter: UserAdapter) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username,
      password
    }, httpOptions);
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(AUTH_API + 'getUserById/' + id, httpOptions);
  }

  register(firstName: string, lastName: string, username: string, email: string, password: string, role: string): Observable<any> {
    return this.http.post(AUTH_API + 'signUp', {
      firstName,
      lastName,
      username,
      email,
      password,
      role
    }, httpOptions);
  }

  getUsers(): Observable<any> {
     return this.http.get(AUTH_API + 'getUsers', { });
   }
}
