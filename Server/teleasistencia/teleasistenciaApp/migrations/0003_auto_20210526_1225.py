# Generated by Django 2.2.7 on 2021-05-26 10:25

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('teleasistenciaApp', '0002_auto_20210521_1349'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='historico_tipo_situacion',
            name='id_terminal_alarma',
        ),
        migrations.AddField(
            model_name='historico_tipo_situacion',
            name='id_terminal',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='teleasistenciaApp.Terminal'),
        ),
        migrations.AlterField(
            model_name='agenda',
            name='fecha_resolucion',
            field=models.DateField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='agenda',
            name='observaciones',
            field=models.CharField(blank=True, max_length=4000),
        ),
        migrations.AlterField(
            model_name='paciente',
            name='intereses_y_actividades',
            field=models.CharField(blank=True, max_length=6000),
        ),
        migrations.AlterField(
            model_name='paciente',
            name='numero_expediente',
            field=models.CharField(blank=True, max_length=200),
        ),
        migrations.AlterField(
            model_name='paciente',
            name='observaciones_medicas',
            field=models.CharField(blank=True, max_length=6000),
        ),
        migrations.AlterField(
            model_name='paciente',
            name='prestacion_otros_servicios_sociales',
            field=models.CharField(blank=True, max_length=2000),
        ),
        migrations.AlterField(
            model_name='persona',
            name='fecha_nacimiento',
            field=models.DateField(blank=True),
        ),
        migrations.AlterField(
            model_name='persona',
            name='telefono_fijo',
            field=models.CharField(blank=True, max_length=20),
        ),
        migrations.AlterField(
            model_name='persona',
            name='telefono_movil',
            field=models.CharField(blank=True, max_length=20),
        ),
        migrations.AlterField(
            model_name='relacion_paciente_persona',
            name='disponibilidad',
            field=models.CharField(blank=True, max_length=200),
        ),
        migrations.AlterField(
            model_name='relacion_paciente_persona',
            name='observaciones',
            field=models.CharField(blank=True, max_length=4000),
        ),
        migrations.AlterField(
            model_name='relacion_paciente_persona',
            name='prioridad',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='relacion_paciente_persona',
            name='tiene_llaves_vivienda',
            field=models.BooleanField(blank=True, default=False),
        ),
        migrations.AlterField(
            model_name='terminal',
            name='barreras_arquitectonicas',
            field=models.CharField(blank=True, max_length=5000),
        ),
        migrations.AlterField(
            model_name='terminal',
            name='id_titular',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='teleasistenciaApp.Paciente'),
        ),
    ]
