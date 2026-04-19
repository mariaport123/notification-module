import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicAnnouncementComponent } from './public-announcement';

describe('PublicAnnouncement', () => {
  let component: PublicAnnouncementComponent;
  let fixture: ComponentFixture<PublicAnnouncementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicAnnouncementComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PublicAnnouncementComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
