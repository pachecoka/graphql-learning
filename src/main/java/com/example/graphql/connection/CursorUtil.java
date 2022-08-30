package com.example.graphql.connection;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class CursorUtil {

    public ConnectionCursor createCursorWith(UUID id) {
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(id.toString().getBytes(UTF_8)));
    }

    public UUID decode(String cursorId) {
        return UUID.fromString(new String(Base64.getDecoder().decode(cursorId)));
    }

    public <T> ConnectionCursor getStartCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    public <T> ConnectionCursor getEndCursorFrom(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
    }
}
