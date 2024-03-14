import { TestBed } from '@angular/core/testing';

import { UtilityComponentsLibService } from './utility-components-lib.service';

describe('UtilityComponentsLibService', () => {
  let service: UtilityComponentsLibService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtilityComponentsLibService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
