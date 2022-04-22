import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaRelacionTerminalRecursosComunitariosComponent } from './nueva-relacion-terminal-recursos-comunitarios.component';

describe('NuevaRelacionTerminalRecursosComunitariosComponent', () => {
  let component: NuevaRelacionTerminalRecursosComunitariosComponent;
  let fixture: ComponentFixture<NuevaRelacionTerminalRecursosComunitariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevaRelacionTerminalRecursosComunitariosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevaRelacionTerminalRecursosComunitariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
