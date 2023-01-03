import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reward, RewardAdapter } from "../model/reward.model";
import { map } from "rxjs/operators";

const API_URL = 'http://localhost:8888/reward/';

@Injectable({
  providedIn: 'root'
})
export class RewardService {
   constructor(private http: HttpClient, private adapter: RewardAdapter) { }
   getRewards(): Observable<any> {
     return this.http.get(API_URL, { });
   }
}
