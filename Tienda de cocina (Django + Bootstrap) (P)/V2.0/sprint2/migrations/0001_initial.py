# Generated by Django 4.2.6 on 2023-11-19 00:25

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Categoria',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(help_text='Ingresa nombre Categoría', max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Cuenta',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(help_text='Ingresa tu nombre', max_length=100)),
                ('apellido', models.CharField(help_text='Ingresa tu apellido', max_length=100)),
                ('fecha_nac', models.DateField(help_text='Indica tu fecha de nacimiento', null=True)),
                ('correo', models.EmailField(blank=True, help_text='Correo personal', max_length=254, null=True)),
            ],
        ),
        migrations.CreateModel(
            name='Producto',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(help_text='Ingresa nombre Producto', max_length=100)),
                ('stock', models.IntegerField(help_text='Stock de Producto')),
                ('precio', models.IntegerField(help_text='Precio de Producto', null=True)),
                ('imagen', models.ImageField(null=True, upload_to='productos')),
                ('descripcion', models.CharField(help_text='Ingresa descripción del producto', max_length=200, null=True)),
                ('categoria', models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='sprint2.categoria')),
            ],
        ),
    ]