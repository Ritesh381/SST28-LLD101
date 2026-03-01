The exporter sub-classes (PDF, CSV, JSON) behaed dirrerently

- PDF : rejects long text
- CSV : Changes data , --> \n
- JSON : handles null differently.

We are not able to get a commong behaviour of childs. Breaking LSP because it says that : subtypes must be replaceable for their base types without breaking correctness

Now instead of applying export in all subclasses we do it in exporter class. 

Exporter.java
``` java
export() {
    // checks null
    // normalize input
    // encode (this will be abstract method as each file type will have different encoding)
    // creates result
    // return
}
```

Now the sub classes have to implemet 2 methods. contentType() and encode()
Other things are handled inside main Exporter class.