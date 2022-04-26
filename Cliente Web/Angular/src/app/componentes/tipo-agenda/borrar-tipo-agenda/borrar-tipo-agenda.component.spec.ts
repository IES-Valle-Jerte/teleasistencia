import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrarTipoAgendaComponent } from './borrar-tipo-agenda.component';

describe('BorrarTipoAgendaComponent', () => {
  let component: BorrarTipoAgendaComponent;
  let fixture: ComponentFixture<BorrarTipoAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrarTipoAgendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrarTipoAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
