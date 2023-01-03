import { Injectable } from "@angular/core";
import { User } from "user.model";
import { Question } from "question.model";
import { Answer } from "answer.model";

export class saveQuestionnaire {
  constructor(
    public userQuestionnaireId: string,
    public user: User,
    public question: Question,
    public answer: Answer,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class saveQuestionnaireAdapter {
  adapt(item: any): saveQuestionnaire {
    return new saveQuestionnaire(item.userQuestionnaireId, item.user, item.question, item.answer, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
