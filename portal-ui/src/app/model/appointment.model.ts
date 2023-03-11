import { Injectable } from "@angular/core";

export class Appointment {
  constructor(
    public userId: string,
    public doctorId: string,
    public date: string,
    public time: string,
    public reason: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string,
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class AppointmentAdapter {
  adapt(item: any): Appointment {
    return new Appointment(item.userId, item.doctorId, item.date,
    item.time, item.reason, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
