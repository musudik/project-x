import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { ProgramService } from '../_services/program.service';
import { User } from "../model/user.model";
import { Program } from "../model/program.model";
import { Activity } from "../model/activity.model";

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
  showProfile = false;
  showActivities = false;
  public msg:string;
  public users: User[];
  public programs: Program[];
  public activities: any[];
  public selectedActivities: any[];
  currentUser: any;
  currentProgram: string;

  constructor(private userService: UserService, private authService: AuthService, private tokenStorage: TokenStorageService,
              private programService: ProgramService) {
    this.users = [];
    this.programs = [];
    this.activities = [];
    this.selectedActivities = [];
    this.currentUser = '';
    this.currentProgram = '';
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
          this.showProfile = false;
          this.showActivities = false;
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
        this.showProfile = false;
        this.showActivities = false;
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
        this.showProfile = false;
        this.showActivities = false;
      },
      err => {
        this.msg = err.error.message;
      });
  }

  showProfileDiv(userId:string) {

    this.authService.getUserById(userId).subscribe((data:any) => {
      console.log('selectedUser==>', data);
      this.currentUser = data.output;
      this.showUsers = true;
      this.showApprovals = false;
      this.showPrograms = false;
      this.showProfile = true;
      this.showActivities = false;
    }, err => {
     this.msg = err.error.message;
    });
  }

  showActivitiesDiv(userId:string, programId:string) {

    this.currentProgram = programId;
    this.selectedActivities = [];
    this.programService.getUserActivities(programId, userId).subscribe((data) => {
      console.log('response==>', data);
      this.selectedActivities = data.output;
      console.log("Activities of User is "+this.selectedActivities);
    });

    this.programService.getProgramById(programId).subscribe((data) => {
      console.log('response==>', data);
      this.activities = data.activities;
      console.log("Activities Full List is "+this.activities[0].activityId);
      this.showActivities = true;
        for (let i = 0; i < this.activities.length; i++) {
            if(this.selectedActivities.includes(this.activities[i].activityId)) {
              console.log("MATCH:::: "+this.activities[i]);
              this.activities[i].selected = true;
            }
        }
    });
  }
}
