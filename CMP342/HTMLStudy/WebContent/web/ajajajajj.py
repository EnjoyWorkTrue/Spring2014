
x = ["hello","bye00","goodA"]


for i in range(len(x[0])):
    total = []
    for j in range(len(x)):
        total.append(x[j][i])

    print(total)
    print(",".join(total))

        
        
