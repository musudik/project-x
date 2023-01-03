import { Injectable } from "@angular/core";

export class Questionnaire {
  constructor(
    public questionnaireId: string,
    public question: string,
    public answer: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class QuestionnaireAdapter {
  adapt(item: any): Questionnaire {
    return new Questionnaire(item.questionnaireId, item.question, item.answer, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
