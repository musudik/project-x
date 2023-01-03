import { Injectable } from "@angular/core";

export class Reward {
  constructor(
    public rewardId: string,
    public reward_name: string,
    public description: string,
    public createdDate: string,
    public createdBy: string,
    public updatedDate: string,
    public updatedBy: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class RewardAdapter {
  adapt(item: any): Reward {
    return new Reward(item.rewardId, item.reward_name, item.description, item.createdDate, item.createdBy, item.updatedDate, item.updatedBy);
  }
}
