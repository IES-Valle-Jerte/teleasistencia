import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrarAgendaComponent } from './borrar-agenda.component';

describe('BorrarAgendaComponent', () => {
  let component: BorrarAgendaComponent;
  let fixture: ComponentFixture<BorrarAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrarAgendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrarAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
