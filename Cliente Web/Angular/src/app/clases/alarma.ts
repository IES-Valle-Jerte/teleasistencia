import {IAlarma} from "../interfaces/i-alarma";


export class Alarma implements IAlarma{
  id: number;
  estado_alarma: string;
  fecha_registro: Date;
  observaciones: string;
  resumen: string;
  id_tipo_alarma: any;
  id_teleoperador: any;
  id_paciente_ucr: any;
  id_terminal: any;



}
