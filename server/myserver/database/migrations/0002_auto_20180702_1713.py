# Generated by Django 2.0.6 on 2018-07-02 09:13

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('database', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='class',
            name='count',
            field=models.CharField(max_length=40),
        ),
        migrations.AlterField(
            model_name='class',
            name='number',
            field=models.CharField(max_length=40),
        ),
        migrations.AlterField(
            model_name='class',
            name='rid',
            field=models.CharField(max_length=40),
        ),
        migrations.AlterField(
            model_name='member',
            name='rid',
            field=models.CharField(max_length=40),
        ),
        migrations.AlterField(
            model_name='schedule',
            name='results',
            field=models.CharField(max_length=40),
        ),
        migrations.AlterField(
            model_name='schedule',
            name='rid',
            field=models.CharField(max_length=40),
        ),
    ]
