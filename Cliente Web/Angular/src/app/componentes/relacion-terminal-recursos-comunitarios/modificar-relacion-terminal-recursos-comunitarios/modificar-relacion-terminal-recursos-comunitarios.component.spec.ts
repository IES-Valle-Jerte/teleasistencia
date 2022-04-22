import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarRelacionTerminalRecursosComunitariosComponent } from './modificar-relacion-terminal-recursos-comunitarios.component';

describe('ModificarRelacionTerminalRecursosComunitariosComponent', () => {
  let component: ModificarRelacionTerminalRecursosComunitariosComponent;
  let fixture: ComponentFixture<ModificarRelacionTerminalRecursosComunitariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificarRelacionTerminalRecursosComunitariosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificarRelacionTerminalRecursosComunitariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
