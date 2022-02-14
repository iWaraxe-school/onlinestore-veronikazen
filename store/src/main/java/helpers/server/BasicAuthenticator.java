package helpers.server;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

public class BasicAuthenticator extends Authenticator {

    @Override
    public Result authenticate(HttpExchange exch) {
        if ("/forbidden".equals(exch.getRequestURI().toString()))
            return new Failure(403);
        else
            return new Success(new HttpPrincipal("user", "password"));
    }
}
