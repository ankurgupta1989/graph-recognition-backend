package service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 * Created by ankurgupta on 11/24/14.
 */

public class RecognizerService extends Service<RecognizerConfiguration> {

    public static void main(String[] args) throws Exception {
        new RecognizerService().run(args);
    }

    @Override
    public void initialize(Bootstrap<RecognizerConfiguration> bootstrap) {
        bootstrap.setName("recognizer-service");
    }

    @Override
    public void run(RecognizerConfiguration configuration,
                    Environment environment) {
        environment.addFilter(CrossOriginFilter.class, "/*")

                .setInitParam("allowedOrigins", "*")
                .setInitParam("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin")
                .setInitParam("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        environment.addResource(new RecognizerResource());
    }
}