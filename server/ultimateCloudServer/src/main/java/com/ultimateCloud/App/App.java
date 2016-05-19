package com.ultimateCloud.App;

import com.ultimateCloud.App.callbacks.AskAuthorise;
import com.ultimateCloud.App.callbacks.Callbackdropbox;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import webSockets.EventServlet;
import webSockets.WebSocketLeBonNuage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       // System.out.println(new Dropbox().getFileList());

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);


        ServletContextHandler handler = new ServletContextHandler(server, "/lebonnuage");
        handler.addServlet(Callbackdropbox.class, "/");

        ServletContextHandler handlerCallbackdropboxauthorise = new ServletContextHandler(server, "/lebonnuage/callbackdropboxauthorise");
        handlerCallbackdropboxauthorise.addServlet(Callbackdropbox.class, "/");


        ServletContextHandler handlerAskAuthorise = new ServletContextHandler(server, "/lebonnuage/askauthorise");
        handlerAskAuthorise.addServlet(AskAuthorise.class, "/");

        HandlerCollection collection = new HandlerCollection();
        collection.addHandler(handlerCallbackdropboxauthorise);
        collection.addHandler(handlerAskAuthorise);

        try
        {
            ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
            handler.addServlet(holderEvents, "/REST/*");
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(handler);

            // Add WebSocket endpoint to javax.websocket layer
            wscontainer.addEndpoint(WebSocketLeBonNuage.class);
            collection.addHandler(handler);
            server.setHandler(collection);
            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }



    }
}
