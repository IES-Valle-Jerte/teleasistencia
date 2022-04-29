import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarAlarmaComponent } from './modificar-alarma.component';

describe('ModificarAlarmaComponent', () => {
  let component: ModificarAlarmaComponent;
  let fixture: ComponentFixture<ModificarAlarmaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificarAlarmaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificarAlarmaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
