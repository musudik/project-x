import { Injectable } from "@angular/core";

export class User {
  constructor(
    public id: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public username: string,
    public password: string,
    public programs: [],
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string,
    public accessToken: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class UserAdapter {
  adapt(item: any): User {
    return new User(item.id, item.firstName, item.lastName, item.email, item.username, item.password,
    item.programs, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy, item.accessToken);
  }
}
