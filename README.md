# Revlet

Revlet is designed to be an abstraction of the Java Servlet API to provide an easy way of learning the basics of HTTP handling with Java. There are no annotations, no reflection, and no lambdas - handlers are set up by simply overriding methods, similar to Servlets, which means that there isn't a big jump from core Java and OOP concepts.

## Getting Started

After cloning this repository, simply package it using maven and add the shaded .jar to the build path of your project.

``` bash
mvn package
```

Once you've done so, you can start creating handlers for different endpoints, like below:

``` java
import com.revature.revlet.http.Handler;
import com.revature.revlet.http.HttpReq;
import com.revature.revlet.http.HttpResp;

public class HelloHandler extends Handler {
    public HelloHandler() {
        // set the path here - preceding/trailing slashes are optional
        super("/hello");
    }

    @Override
    protected void get(HttpReq req, HttpResp resp) {
        resp.writeResponseBody("Hello world!");
    }
}
```

Then, instantiate your handler(s) and start the app from your main method.

``` java
import com.revature.revlet.RevletApp;

public class RevletDemo {
    public static void main (String[] args) {
        HelloHandler handler = new HelloHandler();
        RevletApp.start();
    }
}
```

More documentation is coming soon!