import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { ProgramService } from '../_services/program.service';
import { User } from "../model/user.model";
import { Program } from "../model/program.model";

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content?: string;
  showUsers = false;
  showApprovals = false;
  showPrograms = false;
  public msg:string;
  public users: User[];
  public programs: Program[];

  constructor(private userService: UserService, private authService: AuthService, private tokenStorage: TokenStorageService,
              private programService: ProgramService) {
    this.users = [];
    this.programs = [];
  }

  ngOnInit(): void {
    this.userService.getAdminBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  showUsersTable() {
      const user = this.tokenStorage.getUser();
      let userId = user.id;
      console.log('userId==>', userId);

      this.authService.getUsers().subscribe((data) => {
          console.log('response==>', data);
          this.users = data.output;
          this.showUsers = true;
          this.showApprovals = false;
          this.showPrograms = false;
        },
        err => {
          this.msg = err.error.message;
        });
    }

  showApprovalsTable() {
    const user = this.tokenStorage.getUser();
    let userId = user.id;
    console.log('userId==>', userId);

    this.authService.getUsers().subscribe((data) => {
        console.log('response==>', data);
        this.users = data.output;
        this.showUsers = false;
        this.showApprovals = true;
        this.showPrograms = false;
      },
      err => {
        this.msg = err.error.message;
      });
  }

  showProgramsTable() {
    const user = this.tokenStorage.getUser();
    let userId = user.id;
    console.log('userId==>', userId);

    this.programService.getPrograms().subscribe((data) => {
        console.log('response==>', data);
        this.programs = data;
        this.showUsers = false;
        this.showApprovals = false;
        this.showPrograms = true;
      },
      err => {
        this.msg = err.error.message;
      });
  }
}
