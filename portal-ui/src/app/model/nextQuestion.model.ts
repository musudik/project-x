import { Injectable } from "@angular/core";

export class NextQuestion {
  constructor(
    public questionId: number,
    public level: number,
    public category: string,
    public answer: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class NextQuestionAdapter {
  adapt(item: any): NextQuestion {
    return new NextQuestion(item.questionId, item.level, item.category, item.answer);
  }
}
