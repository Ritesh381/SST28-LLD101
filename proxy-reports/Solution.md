## Problem
So the fileReport was directly accessed in App.java, so everytime the app is run it loads the FileReport which is a heavy operation.

Clients depend directly on the concrete implementation

There is a accessControl class but was never used.


## Solution
First I changed ReportFile to Report, creating abstraction for the client. 

I Shifted the logic of ReportFile into RealReport, now we don't use ReportFIle anywhere. 

Cached the results of laodFromDisk in a hashmap

In reportProxy implemented lazyLoading of ReportFile and also used accesscontrol in reportProxy.

Now reportViewer, and client both depends on abstraction Report and not concrete classes.