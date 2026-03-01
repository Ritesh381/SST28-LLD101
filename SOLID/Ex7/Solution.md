The SmartClassroomDevice was having so many methods which all the devices needed to be implemented even if they don't use it which violates interface segregation principle. 

- Projector uses only On, Off and ConnectInput methods others are invalid and are never used.
- Light uses off and brightness
- Attendance System only uses scanAttendance
- AC uses only off and setTemperature

So instead of putting up all the methods for all the devices we can create different new interface like CanBeTurnOn, CanBeTurnOff, Connectable, Scannable, Temperature, brightness and implement them in whichever class needs it and not all. This way each subclass will only implement the methods it needs to and not all.

