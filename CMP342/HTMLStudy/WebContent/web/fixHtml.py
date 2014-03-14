import webbrowser,os.path
def add():
    infile = open("hello.html","r")
    x = infile.read()
    infile.close()

    name = input("Title: ")
    link = input("Link: ")
    if "now:" in link.lower():
        link = "\"https://"+link[4:]+"\""
    else:
        if "www" not in link:
            link = "www."+link
        link = "\"https://{0}\"".format(link)
    


    
    final = "<p id=\"firstname\"><a href={0}>{1}</a></p>".format(link,name)
    print(final)
    number = 0
    while number!=-1:
        real = number
        number = x.find("</p>",number+1)

    x = x[:real+5]+final+"\n"+x[real+5:]

    infile = open("hello.html","w")
    infile.write(x)
    infile.close()

def delete():
    infile = open("hello.html","r")
    x = infile.read()
    infile.close()
    x = x.split("\n")
    title = input("what is your title: ")
    for i,des in enumerate(x):
        if title.lower() in des.lower():
            x.pop(i)
            break

    x = "\n".join(x)

    outfile = open("hello.html","w")
    outfile.write(x)
    outfile.close()
    
def play():
    webbrowser.open(os.path.abspath("hello.html"))
            
            
def add_des():
    infile = open("hello.html","r")
    x = infile.read()
    x = x.split("\n")
    infile.close()
    title = input("Added Des: ")
    for i,des in enumerate(x):
        if '</body>' in des:
            num = i
    full = "<p>{0}</p>".format(title)
    x.insert(num,full)
        
    outfile = open("hello.html","w")
    outfile.write("\n".join(x))
    outfile.close()
    
def delete_des():
    infile = open("hello.html","r")
    x = infile.read()
    x = x.split('\n')
    infile.close()
    title = input("Delete: ")
    for i,des in enumerate(x):
        if title.lower() in des.lower():
            num = i
    x.pop(num)
    outfile = open("hello.html","w")
    outfile.write("\n".join(x))
    outfile.close()
    
