package com.microsoft.spring.data.gremlin.conversion.source.code;

import com.microsoft.spring.data.gremlin.conversion.source.GremlinSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

import java.util.List;

public interface GremlinTraversalBuilder {
    /**
     * build the insert query from source (Vertex, Edge or Graph).
     */
    GraphTraversal buildInsertTraversal(GremlinSource source, GraphTraversalSource g);

    /**
     * build the deleteAll query from source (Vertex, Edge or Graph).
     */
    List<GraphTraversal> buildDeleteAllTraversal();

    /**
     * build the deleteAll By Domain Class query from source (Vertex, Edge or Graph).
     */
    List<GraphTraversal> buildDeleteAllByClassTraversal(GremlinSource source, GraphTraversalSource g);

    /**
     * build the findById query from source (Vertex, Edge).
     */
    GraphTraversal buildFindByIdTraversal(GremlinSource source, GraphTraversalSource g);

    /**
     * build the update query from source (Vertex, Edge or Graph).
     */
    List<GraphTraversal> buildUpdateTraversal(GremlinSource source, GraphTraversal traversal);

    /**
     * build the findAll query from source (Vertex, Edge or Graph).
     */
    List<GraphTraversal> buildFindAllTraversal(GremlinSource source, GraphTraversal traversal);

    /**
     * build the DeleteById query from source (Vertex, Edge or Graph).
     */
    List<GraphTraversal> buildDeleteByIdTraversal(GremlinSource source, GraphTraversal traversal);

    /**
     * build the Count query from Source (Vertex, Edge)
     */
    List<GraphTraversal> buildCountTraversal(GremlinSource source, GraphTraversal traversal);

    
}
