import { Injectable } from '@angular/core';

import { NextQuestion, NextQuestionAdapter } from "../model/nextQuestion.model";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const NEXT_QUESTION_INPUT = 'next-question';
const RECON_PROGRAM = 'recon-program';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public saveNextQuestion(nextQuestionInput: any): void {
    this.removeProgram();
    window.sessionStorage.setItem(NEXT_QUESTION_INPUT, JSON.stringify(nextQuestionInput));
  }

  public removeNextQuestion(): void {
    window.sessionStorage.removeItem(NEXT_QUESTION_INPUT);
  }

  public getNextQuestion(): any | null {
    const nextQuestion = window.sessionStorage.getItem(NEXT_QUESTION_INPUT);
    if (nextQuestion) {
      return JSON.parse(nextQuestion);
    }

    return {};
  }

  public saveProgram(program: string): void {
    this.removeProgram();
    window.sessionStorage.setItem(RECON_PROGRAM, program);
  }

  public removeProgram(): void {
    window.sessionStorage.removeItem(RECON_PROGRAM);
  }

  public getProgram(): string | null {
    return window.sessionStorage.getItem(RECON_PROGRAM);
  }
}
