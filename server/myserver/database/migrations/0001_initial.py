# Generated by Django 2.0.6 on 2018-07-01 08:21

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Class',
            fields=[
                ('classid', models.IntegerField(primary_key=True, serialize=False)),
                ('rid', models.IntegerField()),
                ('classname', models.CharField(max_length=40)),
                ('number', models.IntegerField()),
                ('count', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='Member',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('rid', models.IntegerField()),
                ('name', models.CharField(max_length=40)),
                ('password', models.CharField(max_length=40)),
                ('status', models.IntegerField()),
                ('identity', models.CharField(max_length=40)),
            ],
        ),
        migrations.CreateModel(
            name='Schedule',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('rid', models.IntegerField()),
                ('classid', models.IntegerField()),
                ('results', models.IntegerField()),
            ],
        ),
    ]