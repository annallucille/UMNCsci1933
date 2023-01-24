

########################### Milestone 2 ##############################


def most_common(string):
    dictionary = {}
    for char in string: 
        if char in dictionary:
            dictionary[char]+=1
        else:
            dictionary[char]=1
    char = ''
    charValue = 0
    for item in dictionary:
        if dictionary[item] >= charValue:
            char = item
            charValue = dictionary[item]
    print('(',char,',',charValue,')')
    
    
########################### Milestone 3 ##############################
 
def palindrome(string):
    if len(string)<=1:
        return True
    elif string[0]==string[-1]:
        return palindrome(string[1:-1])
    else:
        return False
    


########################### Milestone 4 ##############################

class Circle:
    def ___init____(self,radius=0):
        self.radius = radius
    def getRadius(self):
        return self.radius
    def setRadius(self, radius):
        self.radius = radius
    def getArea(self):
        return 3.14*(self.radius**2)
    def getDiameter(self):
        return self.radius*2
    def getCircumference(self):
        return 2*3.14*self.radius
    def __eq__(self,y):
        if self.radius == y.radius:
            return True
        else:
            return False

if __name__ == '__main__':
    x = Circle()
    y = Circle()
    
    x.setRadius(3)
    y.setRadius(4)
    
    print(x.getArea())
    print(y.getCircumference())
    
    print(x.__eq__(y))
    
    y.setRadius(3)
    print(x.__eq__(y))