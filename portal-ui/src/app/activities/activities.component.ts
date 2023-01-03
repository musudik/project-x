import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProgramService } from '../_services/program.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Program } from "../model/program.model";
import { Activity } from "../model/activity.model";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

  public msg:string;
  public reconProgram: any;
  public activities: any[]
  public selectedActivities: any[]
  public program:string;

  techList = [
      {id: 101, lang: 'Java'},
      {id: 102, lang: 'Angular'},
      {id: 103, lang: 'Hibernate'}
    ];
  activityForm = this.formBuilder.group({
    selectedActivity: ''
  });

  constructor(private programService: ProgramService, private tokenStorage: TokenStorageService, private formBuilder: FormBuilder) {
    this.msg = '';
  }

  onFormSubmit() {
    console.log(this.activityForm.get('selectedActivity').value);
  }

  ngOnInit() {
    const user = this.tokenStorage.getUser();
    let userId = user.id;

    this.program = this.tokenStorage.getProgram();
    console.log("Selected Program Activity is "+this.program);
    this.programService.getProgramById(this.program).subscribe((data) => {
      console.log('response==>', data);
      this.reconProgram = data;
      this.activities = data.activities;
      console.log("Activities Full List is "+this.activities);
    });

    console.log("Get getUserActivities "+this.program);
    this.programService.getUserActivities(this.program, userId).subscribe((data) => {
      console.log('response==>', data);
      this.selectedActivities = data.output;
      console.log("Activities By User is "+this.selectedActivities);
      for (let i = 0; i < this.activities.length; i++) {
          if(this.selectedActivities.includes(this.activities[i].activityId)) {
            console.log(this.activities[i]);
            this.activities[i].selected = true;
          }
      }
    });
  }

  updateActivities(): void {
     let activities = this.activityForm.get('selectedActivity').value + "";
     const user = this.tokenStorage.getUser();
     let userId = user.id;
     console.log('program==>', this.program);
     console.log('activities==>', activities.split(","));
     console.log('userId==>', userId);
     console.log('complete==>', true);
     this.programService.createActivity(this.program, activities.split(","), userId, "true").subscribe(
          data => {
          console.log(data);
             this.msg='Activities Saved ';
          },
          err => {
            this.msg = err.error.message;
          }
       );
  }
}
