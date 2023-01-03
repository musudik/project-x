import { Injectable } from "@angular/core";

export class Question {
  constructor(
    public questionId: number,
    public question: string,
    public answers: [],
    public category: string,
    public questionLevel: number,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class QuestionAdapter {
  adapt(item: any): Question {
    return new Question(item.questionId, item.question, item.answers, item.category, item.questionLevel, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
