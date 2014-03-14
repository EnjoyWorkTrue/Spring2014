import pymysql

nameOfDb = "tennis"
conn = pymysql.connect(db=nameOfDb, user='root', passwd='nbuser', host='localhost', port=3306)
cur = conn.cursor()



def add():
    cur.execute('show tables from {nameOfDb}'.format(nameOfDb))
    listofTable = []
    for i,des in enumerate(cur):
        print(i,des[0])
        listofTable.append(des[0])
    num = int(input("put number: "))
    cur.execute('show columns from {0}'.format(listofTable[num]))
    x = []
    for i in cur:
        x.append(input(i[0]+": "+i[1]+": "))
    value = ','.join(x)
    cur.execute('insert into {0} values ({1})'.format(listofTable[num],value))

def commit():
    conn.commit()

def showtables():
    cur.execute("show tables from {0}".format(nameOfDb))
    for i in cur:
        print(i)

    
def close():
    cur.close()
    conn.close()