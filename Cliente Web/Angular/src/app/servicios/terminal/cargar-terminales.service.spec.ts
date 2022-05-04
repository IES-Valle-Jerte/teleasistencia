import { TestBed } from '@angular/core/testing';

import { CargarTerminalesService } from './cargar-terminales.service';

describe('CargarAlarmasService', () => {
  let service: CargarTerminalesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CargarTerminalesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
