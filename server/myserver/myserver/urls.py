"""myserver URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import url
from database import views

urlpatterns = [
	url(r'^$', views.hello),
    url(r'^memberinterface_register/(\w+)/(\w+)/(\w+)/(\d+)/(\w+)/$', views.memberinterface_register),
    url(r'^memberinterface_login/(\w+)/(\w+)/(\w+)/$', views.memberinterface_login),
    url(r'^classinterface_admindata/$', views.classinterface_admindata),
    url(r'^classinterface_getchoosedata/(\w+)/$', views.classinterface_getchoosedata),
    url(r'^classinterface_getdropdata/(\w+)/$', views.classinterface_getdropdata),
    url(r'^memberinterface_change/(\w+)/(\w+)/(\w+)/$', views.memberinterface_change),
    url(r'^memberinterface_deletestudent/(\w+)/$', views.memberinterface_deletestudent),
    url(r'^memberinterface_deleteteacher/(\w+)/$', views.memberinterface_deleteteacher),
    url(r'^scheduleinterface_adddata/(\d+)/(\d+)/(\d+)/$', views.scheduleinterface_adddata),
    url(r'^scheduleinterface_dropclass/(\d+)/(\d+)/$', views.scheduleinterface_dropclass),
    url(r'^classinterface_getclasscount/(\d+)/$', views.classinterface_getclasscount),
    url(r'^scheduleinterface_getresultdata/(\d+)/$', views.scheduleinterface_getresultdata),
    url(r'^classinterface_adddata/(\w+)/(\w+)/(\w+)/$', views.classinterface_adddata),
    url(r'^classinterface_getData/(\w+)/$', views.classinterface_getData),
    url(r'^classinterface_delete/(\d+)/$',views.classinterface_delete),
    url(r'^scheduleinterface_getData/(\w+)/$', views.scheduleinterface_getData),
    url(r'^scheduleinterface_changeresult/(\w+)/(\w+)/(\w+)/$', views.scheduleinterface_changeresult),
]
