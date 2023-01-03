import { Component, OnInit } from '@angular/core';
import { QuestionnaireService } from '../_services/questionnaire.service';
import { ProgramService } from '../_services/program.service';
import { Questionnaire } from "../model/questionnaire.model";
import { Question } from "../model/question.model";
import { Answer } from "../model/answer.model";
import { NextQuestion } from "../model/nextQuestion.model";
import { Program } from "../model/program.model";

import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {
  public questionnaire: Questionnaire[];
  public msg:string;
  public answer:string;
  public program:string;
  public currentQuestion: any;
  public reconProgram: any;
  public nextQuestion: NextQuestion = new NextQuestion(0, 0, "", "");


  constructor(private questionnaireService: QuestionnaireService, private tokenStorage: TokenStorageService,
              private programService: ProgramService) {
    this.questionnaire = [];
    this.msg = '';
    this.answer = '';
    this.program = '';
  }

  ngOnInit() {
      this.program = this.tokenStorage.getProgram();
      if (this.program == null) {
        this.nextQuestion = this.tokenStorage.getNextQuestion();

        if (this.nextQuestion != null && this.nextQuestion.questionId == 0) {
            this.nextQuestion.questionId = 0;
            this.nextQuestion.level = 0;
            this.nextQuestion.category = "GENERAL";
        }

        console.log('tokenStorage questionId==>', this.nextQuestion.questionId);
        console.log('tokenStorage level==>', this.nextQuestion.level);
        console.log('tokenStorage category==>', this.nextQuestion.category);

        if (this.nextQuestion.questionId > 3) {
          this.nextQuestion.level = this.nextQuestion.level + 1;
          this.nextQuestion.category = this.nextQuestion.answer;
        }

        // (3) Subscribe
        this.questionnaireService.nextQuestion(this.nextQuestion.questionId, this.nextQuestion.level, this.nextQuestion.category).subscribe((data) => {
          console.log('response==>', data);
          // (4) Store
          this.currentQuestion = data;
        });
      } else {
        // (3) Subscribe
        this.programService.getProgramById(this.program).subscribe((data) => {
          console.log('response==>', data);
          // (4) Store
          this.reconProgram = data;
        });
        console.log("Program Recommended is "+this.reconProgram);
      }
  }

  onSelect(question: Question, answer: Answer) {
      console.log('onSelect questionId ====>',question.questionId);
      console.log('onSelect questionLevel ====>', question.questionLevel);
      console.log('onSelect category ====>', question.category);
      console.log('onSelect answer ====>', answer.answerId);
      this.program = answer.recommendedProgram;

      this.nextQuestion = new NextQuestion(question.questionId,question.questionLevel,question.category, answer.answer);
  }

  onNext() {
      this.tokenStorage.saveNextQuestion(this.nextQuestion);
      console.log("SAVED::"+this.tokenStorage.getNextQuestion());
      console.log('tokenStorage nextQuestionInput==>', this.nextQuestion.questionId);
      if(this.program != null) {
          this.tokenStorage.saveProgram(this.program);
      }
      this.reloadPage();
  }

  onEnter(question: Question, answer: string) {
      console.log('onSelect questionId ====>', question.questionId);
      console.log('onSelect questionLevel ====>', question.questionLevel);
      console.log('onSelect category ====>', question.category);
      console.log('onSelect answer ====>', answer);
      this.nextQuestion = new NextQuestion(question.questionId, question.questionLevel, question.category, answer);

      this.tokenStorage.saveNextQuestion(this.nextQuestion);
      console.log("SAVED::"+this.tokenStorage.getNextQuestion());
      console.log('tokenStorage nextQuestionInput==>', this.nextQuestion.questionId);
      this.reloadPage();
  }

  registerProgram(programId:string) {
      const user = this.tokenStorage.getUser();
      let userId = user.id;
      console.log('userId==>', userId);
      let programIds = [];
      programIds.push(programId);
      this.programService.register(userId, programIds).subscribe(
         data => {
         console.log(data);
            this.msg='You are successfully registered for the program ' + programId  + ', please check the programs section';
         },
         err => {
           this.msg = err.error.message;
         }
      );
  }

  startAgain() {
      this.tokenStorage.removeNextQuestion();
      this.tokenStorage.removeProgram();
      this.reloadPage();
  }

  async reloadPage() {
       window.location.reload();
  }
}
