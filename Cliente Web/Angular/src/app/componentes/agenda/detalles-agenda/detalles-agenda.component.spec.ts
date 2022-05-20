import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesAgendaComponent } from './detalles-agenda.component';

describe('DetallesAgendaComponent', () => {
  let component: DetallesAgendaComponent;
  let fixture: ComponentFixture<DetallesAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetallesAgendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetallesAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
