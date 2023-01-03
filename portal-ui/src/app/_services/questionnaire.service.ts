import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Questionnaire, QuestionnaireAdapter } from "../model/questionnaire.model";
import { map } from "rxjs/operators";

const API_URL = 'http://localhost:8888/questionnaire/';
const QUESTION_API_URL = 'http://localhost:8888/programRecon/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {
  constructor(private http: HttpClient, private adapter: QuestionnaireAdapter) { }

   //get all questionnaire
   getQuestionnaire(): Observable<any> {
     return this.http.get(API_URL, { });
   }

   //return the next question based ont the current questionId, level and category
   nextQuestion(questionId: number, level: number, category: string): Observable<any> {
     return this.http.post(QUESTION_API_URL + 'nextQuestion', {
      questionId,
      level,
      category
     }, httpOptions);
   }
}
