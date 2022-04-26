import { TestBed } from '@angular/core/testing';

import { BorrarTipoAgendaService} from "./borrar-tipo-agenda.service";

describe('BorrarTipoAgendaService', () => {
  let service: BorrarTipoAgendaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BorrarTipoAgendaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
