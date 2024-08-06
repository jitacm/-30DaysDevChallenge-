stack=[]
while True:
    print("1.push\n2.pop\n3.display\n4.exit\nEnter your choice:")  # Added exit option
    ch=int(input())
    if(ch==1):
        i=int(input("how many values you want to insert:"))
        for i in range(0,i):
           a=int(input("Enter numbers: "))
           stack.append(a)
           print(stack)
    elif ch==2:
        if stack==[]:
            print("Stack is already empty.")
        else:
            i=int(input("how many values you want to pop:"))
            for i in range(0,i):
               stack.pop()
               print(stack)
    elif ch==3:
        print(stack)
    elif ch==4:
        break