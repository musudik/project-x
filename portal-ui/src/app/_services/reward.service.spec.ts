import { TestBed } from '@angular/core/testing';

import { RewardService } from './rewards.service';

describe('rewardService', () => {
  let service: RewardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(rewardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
