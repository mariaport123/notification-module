import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicAnnouncement } from './public-announcement';

describe('PublicAnnouncement', () => {
  let component: PublicAnnouncement;
  let fixture: ComponentFixture<PublicAnnouncement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicAnnouncement],
    }).compileComponents();

    fixture = TestBed.createComponent(PublicAnnouncement);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
