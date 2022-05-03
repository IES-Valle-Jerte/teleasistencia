import { Injectable } from '@angular/core';
import {IAlarma} from "../../interfaces/i-alarma";
import {CargarAlarmasService} from "./cargar-alarmas.service";
import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ModificarAlarmaResolveService implements Resolve<IAlarma> {

  constructor(private cargarAlarma: CargarAlarmasService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAlarma> {
    return this.cargarAlarma.getAlarma(route.params['id']).pipe(
      catchError(error => {
        this.router.navigate(['/inicio']);
        return of(null)
      })
    );
  }
}
