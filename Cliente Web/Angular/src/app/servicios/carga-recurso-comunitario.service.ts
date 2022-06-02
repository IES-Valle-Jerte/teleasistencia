import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IRecursoComunitario} from '../interfaces/i-recurso-comunitario';
import {ICentroSanitario} from "../interfaces/i-centro-sanitario";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})

export class CargaRecursoComunitarioService {
  private urlBase = environment.urlBase;
  private URL_SERVER_RECURSOS_COMUNITARIOS = this.urlBase + 'recurso_comunitario';

  constructor(private http: HttpClient) {
  }

  getRecursosComunitarios(): Observable<IRecursoComunitario[]> {
    return this.http.get<IRecursoComunitario[]>(this.URL_SERVER_RECURSOS_COMUNITARIOS);
  }

  getRecursoComunitario(idRecursoComunitario: number): Observable<IRecursoComunitario> {
    return this.http.get<IRecursoComunitario>(this.URL_SERVER_RECURSOS_COMUNITARIOS + '/' + idRecursoComunitario);
  }

  modificarRecursoComunitario(recursoComunitario: IRecursoComunitario): Observable<IRecursoComunitario> {
    return this.http.put<IRecursoComunitario>(this.URL_SERVER_RECURSOS_COMUNITARIOS + '/' + recursoComunitario.id, recursoComunitario);
  }

  nuevoRecursoComunitario(recursoComunitario: IRecursoComunitario): Observable<IRecursoComunitario> {
    return this.http.post<IRecursoComunitario>(this.URL_SERVER_RECURSOS_COMUNITARIOS, recursoComunitario);
  }

  eliminarRecursoComunitario(recursoComunitario:IRecursoComunitario): Observable<IRecursoComunitario> {
    return this.http.delete<IRecursoComunitario>(this.URL_SERVER_RECURSOS_COMUNITARIOS + '/' + recursoComunitario.id);
  }
}
