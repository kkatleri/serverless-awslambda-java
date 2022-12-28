package helloworld;

/**
 * Handler for requests to Lambda function.
 */
public class App {

    public String hello(String name){
        return "Hello from Lambda snapstart - " + name;
    }
}