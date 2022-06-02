import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";
import {IDispositivosAuxiliaresTerminal} from "../../interfaces/i-dispositivos-auxiliares-terminal";

@Injectable({
  providedIn: 'root'
})
export class CargaDispositivosAuxiliaresTerminalService {

  private urlBase = environment.urlBase;
  private URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL = this.urlBase + 'dispositivos_auxiliares_en_terminal';

  constructor(private http: HttpClient) { }

  getDispositivosAuxiliaresTerminal(): Observable<IDispositivosAuxiliaresTerminal[]> {
    return this.http.get<IDispositivosAuxiliaresTerminal[]>(this.URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL);
  }

  getDispositivoAuxiliarTerminal(idDispositivosAuxiliaresTerminal: number): Observable<IDispositivosAuxiliaresTerminal> {
    return this.http.get<IDispositivosAuxiliaresTerminal>(this.URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL+ '/' + idDispositivosAuxiliaresTerminal);
  }

  modificarDispositivoAuxiliarTerminal(dispositivosAuxiliaresTerminal: IDispositivosAuxiliaresTerminal): Observable<IDispositivosAuxiliaresTerminal> {
    return this.http.put<IDispositivosAuxiliaresTerminal>(this.URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL+ '/' + dispositivosAuxiliaresTerminal.id, dispositivosAuxiliaresTerminal);
  }

  nuevoDispositivoAuxiliarTerminal(dispositivosAuxiliaresTerminal: IDispositivosAuxiliaresTerminal): Observable<IDispositivosAuxiliaresTerminal> {
    return this.http.post<IDispositivosAuxiliaresTerminal> (this.URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL, dispositivosAuxiliaresTerminal);
  }
  eliminarDispositivoAuxiliarTerminal(dispositivosAuxiliaresTerminal: IDispositivosAuxiliaresTerminal): Observable<IDispositivosAuxiliaresTerminal> {
    return this.http.delete<IDispositivosAuxiliaresTerminal>(this.URL_SERVER_DISPOSITIVOS_AUXILIARES_TERMINAL + '/' + dispositivosAuxiliaresTerminal.id)
  }
}
