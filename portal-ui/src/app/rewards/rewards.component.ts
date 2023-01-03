import { Component, OnInit } from '@angular/core';
import { RewardService } from '../_services/reward.service';
import { Reward } from "../model/reward.model";
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-rewards',
  templateUrl: './rewards.component.html',
  styleUrls: ['./rewards.component.css']
})
export class RewardsComponent implements OnInit {

  public rewards: Reward[];
  public currentUserRewards: Reward[];

  constructor(private rewardService: RewardService, private tokenStorage: TokenStorageService, private authService: AuthService) {
      this.rewards = [];
    }

    ngOnInit() {
        this.rewardService.getRewards().subscribe((data) => {
          console.log('response==>', data);
          this.rewards = data;
        });
        console.log('rewards ====>', this.rewards);

        const user = this.tokenStorage.getUser();
        let userId = user.id;
        console.log('userId==>', userId);
        // (3) Subscribe
        this.authService.getUserById(userId).subscribe((data) => {
          console.log('response==>', data);
          this.currentUserRewards = data.output.rewards;
        });
        console.log('currentUserRewards ====>', this.currentUserRewards);
    }
}
