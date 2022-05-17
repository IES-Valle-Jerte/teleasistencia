import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";
import {IAgenda} from "../interfaces/i-agenda";

@Injectable({
  providedIn: 'root'
})

export class CargaAgendaService {
  private urlBase = environment.urlBase;
  private URL_SERVER_AGENDAS = this.urlBase + 'agenda';

  constructor(private http: HttpClient) {
  }

  getAgendas(): Observable<IAgenda[]> {
    return this.http.get<IAgenda[]>(this.URL_SERVER_AGENDAS);
  }

  getAgenda(idAgenda: number): Observable<IAgenda> {
    return this.http.get<IAgenda>(this.URL_SERVER_AGENDAS + '/' + idAgenda);
  }

  modificarAgenda(agenda: IAgenda): Observable<IAgenda> {
    return this.http.put<IAgenda>(this.URL_SERVER_AGENDAS + '/' + agenda.id, agenda);
  }

  nuevoAgenda(agenda: IAgenda): Observable<IAgenda> {
    return this.http.post<IAgenda>(this.URL_SERVER_AGENDAS, agenda);
  }

  borrarAgenda(idAgenda: number) {
    return this.http.delete<IAgenda>(this.URL_SERVER_AGENDAS + '/' + idAgenda);
  }
}
