import { Injectable } from "@angular/core";

export class Answer {
  constructor(
    public answerId: string,
    public answer: string,
    public question: string,
    public recommendedProgram: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class AnswerAdapter {
  adapt(item: any): Answer {
    return new Answer(item.answerId, item.answer, item.question, item.recommendedProgram, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
