package logger;

public class Task02 {

    public static void main(String[] args) {
        SimpleLogger logger = new SimpleLogger();
        logger.log("Hello");        

        SmartLogger logger2 = new SmartLogger();
        logger2.log("Hello");     
        logger2.log("World"); 
        logger2.log("error message");            
    }
}
