import { Injectable } from "@angular/core";

export class Program {
  constructor(
    public programId: string,
    public program_name: string,
    public description: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string,
    public user: string,
    public active: boolean
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class ProgramAdapter {
  adapt(item: any): Program {
    return new Program(item.programId, item.program_name, item.description,
    item.createdDate, item.createdBy, item.updatedDate, item.updatedBy, item.user, item.active);
  }
}
