import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrarRelacionTerminalRecursosComunitariosComponent } from './borrar-relacion-terminal-recursos-comunitarios.component';

describe('BorrarRelacionTerminalRecursosComunitariosComponent', () => {
  let component: BorrarRelacionTerminalRecursosComunitariosComponent;
  let fixture: ComponentFixture<BorrarRelacionTerminalRecursosComunitariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrarRelacionTerminalRecursosComunitariosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrarRelacionTerminalRecursosComunitariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
