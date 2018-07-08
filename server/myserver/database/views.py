from django.http import HttpResponse
from database import models
import json
import string


def hello(request):
    return HttpResponse("Hello world ! ")


def memberinterface_register(request,rid1,name1,passwrod1,status1,identity1):
    v1 = models.Member.objects.filter(rid=rid1)
    if len(v1):
        return HttpResponse("false")
    else:
        models.Member.objects.create(rid=rid1, name=name1, password=passwrod1, status=status1, identity=identity1)
        return HttpResponse("true")


def memberinterface_login(request,rid1,password1,identity):
    try:
        v1 = models.Member.objects.get(rid=rid1)
        if v1.password != password1:
            return HttpResponse("nopassword")
        if v1.identity != identity:
            return HttpResponse("noidentity")
        if v1.status != 0:
            return HttpResponse("nostatus")
        else:
            # v1.status = 1;
            return HttpResponse("true")
    except:
        return HttpResponse("noid")

def classinterface_admindata(request):
    list=[]
    for temp in models.Member.objects.all():
        temp_list = {
            'name': temp.name,
            'id': temp.rid,
            'identity': temp.identity
        }
        list.append(temp_list)
    #resp= ','.join(str(i) for i in list)
    s = json.dumps(list)
    return HttpResponse(s)

def memberinterface_change(request,rid1 ,name1,password1):
    try:
        v1 = models.Member.objects.get(rid=rid1)
        v1.name = name1
        v1.password = password1
        v1.save()
        return HttpResponse("true")
    except:
        return HttpResponse("false")
def memberinterface_deletestudent(request,rid1):
    list = []
    models.Member.objects.filter(rid=rid1).delete()
    for temp in models.Schedule.objects.filter(rid=rid1):
        list.append(temp.classid)
    for i in list:
        try:
            v1 = models.Class.objects.get(classid=i)
            nowcount= int(v1.count)+1
            print(nowcount)
            models.Class.objects.filter(classid=i).update(count=str(nowcount))
            models.Schedule.objects.get(rid=rid1, classid=i).delete()
            return HttpResponse("true")
        except:
            return HttpResponse("false")
def memberinterface_deleteteacher(request,rid1):
    models.Member.objects.filter(rid=rid1).delete()
    classidlist=[]
    for temp in models.Class.objects.filter(rid=rid1):
        classidlist.append(temp.classid)
    print("here1")
    models.Class.objects.filter(rid=rid1).delete()
    print(("here2"))
    for i in classidlist:
        models.Schedule.objects.filter(classid=i).delete()
    print(("here3"))
    return HttpResponse("here3")
def classinterface_getdropdata(request,rid1):
    classidlist=[]
    countlist=[]
    classnamelist=[]
    idlist=[]
    namelist=[]
    for temp in models.Schedule.objects.filter(rid=rid1):
        classidlist.append(temp.classid)
    for i in classidlist:
        try:
            v1=models.Class.objects.get(classid=i)
            countlist.append(v1.count)
            classnamelist.append(v1.classname)
            idlist.append(v1.rid)
        except:
            return HttpResponse("false")
    for j in idlist:
        try:
            v2=models.Member.objects.get(rid=j)
            namelist.append(v2.name)
        except:
            return HttpResponse("false")
    list = []
    k = 0
    print("here")
    while k < len(classnamelist):
        print("here1")
        k_list={
            "class_name":classnamelist[k],
            "choose_num":countlist[k],
            "teachername":namelist[k],
            "classid":classidlist[k]
        }
        list.append(k_list)
        k=k+1
    s = json.dumps(list, ensure_ascii=False)
    return HttpResponse(s)
def classinterface_getchoosedata(request,rid1):
    scheduleclassidlist=[]
    classidlist=[]
    classnamelist=[]
    countlist=[]
    idlist=[]
    namelist=[]
    for temp in models.Schedule.objects.filter(rid=rid1):
        scheduleclassidlist.append(temp.classid)
    if len(scheduleclassidlist)==0:
        v1=models.Class.objects.all()
        for temp1 in v1:
            classnamelist.append(temp1.classname)
            countlist.append(temp1.count)
            idlist.append(temp1.rid)
            classidlist.append(temp1.classid)
        for i in idlist:
            v2=models.Member.objects.get(rid=i)
            namelist.append(v2.name)
        print(len(classnamelist))
        print(len(countlist))
        print(len(idlist))
        print(len(namelist))
        list = []
        k = 0
        while k < len(idlist):
            k_list = {
                "class_name": classnamelist[k],
                "choose_num": countlist[k],
                "teachername": namelist[k],
                "classid": classidlist[k]
            }
            list.append(k_list)
            k = k + 1
        s = json.dumps(list, ensure_ascii=False)
        return HttpResponse(s)
    else:
        v1 = models.Class.objects.all()
        for temp1 in v1:
            breakflag = False
            for i in scheduleclassidlist:
                if temp1.classid==i:
                    breakflag=True
                    break
            if breakflag==False:
                classidlist.append(temp1.classid)
                classnamelist.append(temp1.classname)
                countlist.append(temp1.count)
                idlist.append(temp1.rid)
        for i in idlist:
            v2 = models.Member.objects.get(rid=i)
            namelist.append(v2.name)
        print(len(classidlist))
        print(len(classnamelist))
        print(len(countlist))
        print(len(idlist))
        print(len(namelist))
        list = []
        k = 0
        while k < len(idlist):
            k_list = {
                "class_name": classnamelist[k],
                "choose_num": countlist[k],
                "teachername": namelist[k],
                "classid": classidlist[k]
            }
            list.append(k_list)
            k = k + 1
        s = json.dumps(list, ensure_ascii=False)
        return HttpResponse(s)
def scheduleinterface_adddata(request,rid1,classid1,result1):
    try:
        v1 = models.Class.objects.get(classid=classid1)
        nowcount = int(v1.count) - 1
        print(nowcount)
        models.Class.objects.filter(classid=classid1).update(count=str(nowcount))
        models.Schedule.objects.create(rid=str(rid1), classid=int(classid1), results=str(result1))
        return HttpResponse("true")
    except:
        return HttpResponse("false")
def scheduleinterface_dropclass(request,rid1,classid1):
    try:
        v1 = models.Class.objects.get(classid=classid1)
        nowcount = int(v1.count) + 1
        print(nowcount)
        models.Class.objects.filter(classid=classid1).update(count=str(nowcount))
        print("here")
        models.Schedule.objects.get(rid=str(rid1), classid=int(classid1)).delete()
        return HttpResponse("true")
    except:
        return HttpResponse("false")
def classinterface_getclasscount(request,classid1):
    v1=models.Class.objects.get(classid=classid1)
    s=v1.count
    return HttpResponse(s)
def scheduleinterface_getresultdata(request,rid1):
    classidlist=[]
    resultlist=[]
    classnamelist=[]
    teacheridlist=[]
    teachernamelist=[]
    v1=models.Schedule.objects.filter(rid=str(rid1))
    for temp in v1:
        classidlist.append(temp.classid)
        resultlist.append(temp.results)
    for i in classidlist:
        v2=models.Class.objects.get(classid=i)
        classnamelist.append(v2.classname)
        teacheridlist.append(v2.rid)
    for j in teacheridlist:
        v3=models.Member.objects.get(rid=j)
        teachernamelist.append(v3.name)
    print(len(classidlist))
    print(len(classnamelist))
    print(len(resultlist))
    print(len(teacheridlist))
    print(len(teachernamelist))
    list = []
    k = 0
    while k < len(classidlist):
        k_list = {
            "class_name": classnamelist[k],
            "teacher_name": teachernamelist[k],
            "result": resultlist[k]
        }
        list.append(k_list)
        k = k + 1
    s = json.dumps(list, ensure_ascii=False)
    return HttpResponse(s)
def classinterface_adddata(request,rid1,classnaem1,number1):
    models.Class.objects.create(rid=rid1,classname=classnaem1,number=number1,count=number1)
    return HttpResponse("true")
def classinterface_getData(request,rid1):
    idlist=[]
    classnamelist=[]
    classidlist=[]
    classnumber=[]
    namelist=[]
    v1 = models.Class.objects.filter(rid=str(rid1))
    for temp in v1:
        idlist.append(temp.rid)
        classnamelist.append(temp.classname)
        classidlist.append(temp.classid)
        classnumber.append(temp.number)
    for i in idlist:
        v2 = models.Member.objects.get(rid=i)
        namelist.append(v2.name)
    list = []
    k = 0
    while k < len(classidlist):
        k_list = {
            "classname": classnamelist[k],
            "teachername": namelist[k],
            "classnumber": classnumber[k],
            "classid":classidlist[k]
        }
        list.append(k_list)
        k = k + 1
    s = json.dumps(list, ensure_ascii=False)
    return HttpResponse(s)
def classinterface_delete(request,classid1):
    models.Class.objects.filter(classid=int(classid1)).delete()
    models.Schedule.objects.filter(classid=int(classid1)).delete()
    return HttpResponse("true")
def scheduleinterface_getData(request,classid1):
    idlist=[]
    resultlist=[]
    namelist=[]
    v1=models.Schedule.objects.filter(classid=classid1)
    for temp in v1:
        idlist.append(temp.rid)
        resultlist.append(temp.results)
    for i in idlist:
        v2=models.Member.objects.get(rid=i)
        namelist.append(v2.name)
    list = []
    k = 0
    while k < len(idlist):
        k_list = {
            "studentname": namelist[k],
            "studentnum": idlist[k],
            "studentgrade": resultlist[k],
        }
        list.append(k_list)
        k = k + 1
    s = json.dumps(list, ensure_ascii=False)
    return HttpResponse(s)
def scheduleinterface_changeresult(request,rid1,classid1,result1):
    models.Schedule.objects.filter(rid=str(rid1), classid=int(classid1)).update(results=str(result1))
    return HttpResponse("true")