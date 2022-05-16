import {IRecursosComunitariosAlarma} from "../interfaces/i-recursos-comunitarios-alarma";
import {IAlarma} from "../interfaces/i-alarma";
import {IRecursoComunitario} from "../interfaces/i-recurso-comunitario";

export class RecursosComunitariosAlarma implements IRecursosComunitariosAlarma{
  id: number;
  fecha_registro: Date;
  persona: string;
  acuerdo_alcanzado: string;
  id_alarma: IAlarma;
  id_recurso_comunitario: IRecursoComunitario;
}
