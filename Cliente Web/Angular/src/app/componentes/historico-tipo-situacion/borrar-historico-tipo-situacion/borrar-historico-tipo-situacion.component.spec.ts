import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrarHistoricoTipoSituacionComponent } from './borrar-historico-tipo-situacion.component';

describe('BorrarHistoricoTipoSituacionComponent', () => {
  let component: BorrarHistoricoTipoSituacionComponent;
  let fixture: ComponentFixture<BorrarHistoricoTipoSituacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrarHistoricoTipoSituacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrarHistoricoTipoSituacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
