import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;

  constructor(private authService: AuthService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    let userId = this.currentUser.id;
    console.log('userId==>', userId);
    // (3) Subscribe
    this.authService.getUserById(userId).subscribe((data:any) => {
      console.log('selectedUser==>', data);
      this.currentUser = data.output;
    });
  }
}
