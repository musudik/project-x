import { Component, OnInit } from '@angular/core';
import { ProgramService } from '../_services/program.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { Program } from "../model/program.model";
import { timer } from 'rxjs';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.css']
})
export class ProgramsComponent implements OnInit {

  public programs: Program[];
  public userPrograms: any;
  public msg:string;

  constructor(private programService: ProgramService, private authService: AuthService, private tokenStorage: TokenStorageService) {
    this.programs = [];
    this.userPrograms = [];
    this.msg = '';
  }

  ngOnInit() {
      const user = this.tokenStorage.getUser();
      let userId = user.id;
      console.log('userId==>', userId);
      // (3) Subscribe
      this.authService.getUserById(userId).subscribe((data) => {
      console.log('selectedUser==>', data);
      // (4) Store
      //this.userPrograms = data.output.programs;
      data.output.programs.forEach( (element:Program) => {
          let pgm:string = element.programId;
          console.log('programIds==>', pgm);
          this.userPrograms.push(pgm);
      });
      console.log('userPrograms==>', this.userPrograms);
      });

      // (3) Subscribe
      this.programService.getPrograms().subscribe((data) => {
      console.log('response==>', data);
      // (4) Store
      this.programs = data;
      });
       console.log('programs ====>', this.programs);
  }

  registerProgram(programId:string) {
    const user = this.tokenStorage.getUser();
    let userId = user.id;
    console.log('userId==>', userId);
    let programIds = [];
    programIds.push(programId);
    this.programService.register(userId, programIds).subscribe(
         data => {
         console.log(data);
            this.msg='You are successfully registered for the program ' +programId;
            //return this.msg;
            this.reloadPage();
         },
         err => {
           this.msg = err.error.message;
         }
      );
  }

  deregisterProgram(programId:string) {
    const user = this.tokenStorage.getUser();
    let userId = user.id;
    console.log('userId==>', userId);
    let programIds = [];
    programIds.push(programId);
    this.programService.deregister(userId, programIds).subscribe(
         data => {
         console.log(data);
            this.msg='You are successfully deregistered for the program ' +programId + ', please reload this page';
            //return this.msg;
            this.reloadPage();
         },
         err => {
           this.msg = err.error.message;
         }
      );
  }

  async reloadPage() {
     await timer(1000).pipe(take(1)).toPromise();
     window.location.reload();
  }

  goToActivities(programId:string) {
     this.tokenStorage.saveProgram(programId);
     window.location.href = "/activities";
  }
}
