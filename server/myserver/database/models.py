from __future__ import unicode_literals
 
from django.db import models
from django.utils.encoding import python_2_unicode_compatible
 
 
@python_2_unicode_compatible
class Member(models.Model):
    rid = models.CharField(max_length=40)
    name = models.CharField(max_length=40)
    password = models.CharField(max_length=40)
    status = models.IntegerField()
    identity = models.CharField(max_length=40)

    def __str__(self):
        return self.name


@python_2_unicode_compatible
class Class(models.Model):
    classid = models.IntegerField(primary_key=True)
    rid = models.CharField(max_length=40)
    classname = models.CharField(max_length=40)
    number = models.CharField(max_length=40)
    count = models.CharField(max_length=40)

    def __str__(self):
        return self.title


@python_2_unicode_compatible
class Schedule(models.Model):
    rid = models.CharField(max_length=40)
    classid = models.IntegerField()
    results = models.CharField(max_length=40)

    def __str__(self):
        return self.name
# Create your models here.
