import { Injectable } from "@angular/core";

export class Activity {
  constructor(
    public activityId: string,
    public activity: string,
    public complete: boolean,
    public description: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class ActivityAdapter {
  adapt(item: any): Activity {
    return new Activity(item.activityId, item.activity, item.complete, item.description, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
