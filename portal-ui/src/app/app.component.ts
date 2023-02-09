import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[] = [];
  role = '';
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  public username:string;

  constructor(private tokenStorageService: TokenStorageService) {
      this.username = '';
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      console.log("Token User:"+user.username);
      this.role = user.role;
      if (this.role === 'admin') {
        this.showAdminBoard = true;
      }
      console.log("role User:"+this.role);
      console.log("showAdminBoard:"+this.showAdminBoard);
//       this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
//       this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
