import {Component, DoCheck, OnInit,} from '@angular/core';
import {LoginService} from "../../servicios/login.service";
import {environment} from "../../../environments/environment";
import {webSocket} from 'rxjs/webSocket';
import Swal from "sweetalert2";
import {Alarma} from "../../clases/alarma";
import {CargaAlarmaService} from "../../servicios/alarmas/carga-alarma.service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {Router} from "@angular/router";
import {ModificarAlarmaResolveService} from "../../servicios/alarmas/modificar-alarma-resolve.service";
import {ProfileService} from "../../servicios/profile.service";
import {IProfileUser} from "../../interfaces/i-profile-user";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, DoCheck {
  //establecemos la direccion para la conexion con el websocket
  subject = webSocket(environment.urlWebsocket);
  public estaLogin: boolean
  public alarmaAModificar: Alarma
  public accion: string
  public teleoperador: number
  public nuevoTeleoperador: number;

  constructor(private loginService: LoginService, private profileService: ProfileService,
              private cargarAlarma: CargaAlarmaService) {
  }

  ngOnInit(): void {
    //comprobamos si hay usuario logeado
    if (this.loginService.estaLogin()) {
      //si hay usuario logeado establecemos conexion websocket
      this.subject.subscribe({
        //si va bien arrancará la funcion para comprobar que hacer
        next: msg => this.comprobarAccion(msg), // Called whenever there is a message from the server.
        error: err => console.log(err), // Called if at any point WebSocket API signals some kind of error.
        complete: () => console.log('complete') // Called when connection is closed (for whatever reason).

      })
    }
  }

  ngDoCheck(): void {
    this.estaLogin = this.loginService.estaLogin()

  }

  //Toast para el Alert indicando que la operación fue exitosa
  alertExito(): void {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      //El tiempo que permanece la alerta, se obtiene mediante una variable global en environment.ts
      timer: environment.timerToast,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'success',
      title: environment.fraseAlarmaAceptada,
    })
  }

  //Toast para el alert indicando que hubo algún error en la operación
  alertError(): void {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: environment.timerToast,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'error',
      title: environment.fraseErrorAsignarAlarma
    })
  }

  //creamomos el metodo que lanzará el modal
  modalAlarma(msg: any): void {
    //asignamos el valor de action a una variable
    this.accion = msg['action']
    //asignamos el valor de alarma a otra variable
    this.alarmaAModificar = msg['alarma']
    //iniciamos el modal mostrando la id y comprobando la procedencia de la alarma
    Swal.fire({
      title: '¡Atención! Nueva alarma ¿Desea Asignarse esta alarma con id ' + this.alarmaAModificar.id + ',' +
        ' asignada al ' + this.comprobarProcedencia(this.alarmaAModificar) + '?',
      showCancelButton: true,
      confirmButtonText: 'Aceptar',
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.asignarTeleoperador()
      }

    })

  }

  // con este metodo se asigna el teleoperador a la alarma y con el servicio se
  // modifica la alarma
  asignarTeleoperador(): void {
    console.log("nuevo" + this.nuevoTeleoperador)
    this.profileService.getProfile()
      .subscribe((resp: IProfileUser[]) => {
        this.alarmaAModificar.id_teleoperador = this.teleoperador = resp[0].id
        this.cargarAlarma.modificarAlarma(this.alarmaAModificar).subscribe(
          e => {
            this.alertExito()
          },
          error => {
            this.alertError()
          })
        }
      )



  }

  //comprobamos el tipo de action que nos llega por parametro
  comprobarAccion(msg): void {
    //si es una alarma nueva abre el modal de alarma

    if (msg['action'] == 'new_alarm') {
      this.modalAlarma(msg)
    }
    // si una alarma fue asignada ya se cierra el modal
    if (msg['action'] == 'alarm_assignment') {
      Swal.close()
    }
  }

  //comprobamos si está a null pacientes ucr para devolver un string
  comprobarProcedencia(msg): string {
    if (msg.id_paciente_ucr) {
      //si no es null devolvemos el paciente ucr con su nombre
      return 'Paciente UCR: ' + msg.id_paciente_ucr.id_persona.nombre + ' ' + msg.id_paciente_ucr.id_persona.apellidos
    }

    //si no ese null el terminal devolvemos  su numero y el titular del mismo
    if (msg.id_terminal)
      return 'Terminal ' + msg.id_terminal.numero_terminal + ', Titular: ' + msg.id_terminal.id_titular.id_persona.nombre + ' ' + msg.id_terminal.id_titular.id_persona.apellidos
  }




}
