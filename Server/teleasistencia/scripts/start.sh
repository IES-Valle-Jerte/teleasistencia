#! /bin/bash

cd /teleasistencia/Server/teleasistencia
nohup python3 manage.py runserver_plus --cert-file ./certificados/teleasistencia.cert --key-file ./certificados/teleasistencia.key teleasistencia.iesvjp.es:443 </dev/null &> ../logs/logs_nohup_$(date +"%F").out  2> ../logs/logs_nohup_error_$(date +"%F").out &