import { TestBed } from '@angular/core/testing';

import { LegoSetService } from './lego-set.service';

describe('LegoSetService', () => {
  let service: LegoSetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LegoSetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
