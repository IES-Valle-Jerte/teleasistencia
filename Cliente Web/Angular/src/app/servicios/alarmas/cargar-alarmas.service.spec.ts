import { TestBed } from '@angular/core/testing';

import { CargarAlarmasService } from './cargar-alarmas.service';

describe('CargarAlarmaService', () => {
  let service: CargarAlarmasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CargarAlarmasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
