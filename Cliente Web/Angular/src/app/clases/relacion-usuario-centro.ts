import {IRelacionUsuarioCentro} from "../interfaces/i-relacion-usuario-centro";
import {IPaciente} from "../interfaces/i-paciente";
import {ICentroSanitario} from "../interfaces/i-centro-sanitario";

export class RelacionUsuarioCentro implements IRelacionUsuarioCentro{
  id: number;
  persona_contacto: string;
  distancia: number;
  tiempo: number;
  observaciones: string;
  id_paciente: IPaciente;
  id_centro_sanitario: ICentroSanitario;
}
