package com.microsoft.spring.data.gremlin.conversion.source.code;

import com.microsoft.spring.data.gremlin.conversion.source.GremlinSource;
import com.microsoft.spring.data.gremlin.conversion.source.GremlinSourceVertex;
import com.microsoft.spring.data.gremlin.exception.GremlinUnexpectedSourceTypeException;
import lombok.NonNull;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.microsoft.spring.data.gremlin.common.Constants.GREMLIN_PRIMITIVE_GRAPH;
import static com.microsoft.spring.data.gremlin.common.Constants.GREMLIN_PRIMITIVE_VERTEX_ALL;
import static com.microsoft.spring.data.gremlin.common.GremlinEntityType.VERTEX;

public class GremlinTraversalBuilderVertex extends AbstractGremlinTraversalBuilder implements GremlinTraversalBuilder  {

    @Override
    public GraphTraversal buildInsertTraversal(@NonNull GremlinSource source, @NonNull GraphTraversalSource g){
        if (!(source instanceof GremlinSourceVertex)) {
            throw new GremlinUnexpectedSourceTypeException("should be the instance of GremlinSourceVertex");
        }

        GraphTraversal traversal =  g.addV(source.getLabel());
        source.getId().ifPresent(id -> generatePropertyWithRequiredId(traversal, id)); // property(id, xxx)
        generateProperties(traversal, source.getProperties());

        return traversal;
    }

    @Override
    public GraphTraversal buildFindByIdTraversal(@NonNull GremlinSource source, @NonNull GraphTraversalSource g) {
        if (!(source instanceof GremlinSourceVertex)) {
            throw new GremlinUnexpectedSourceTypeException("should be the instance of GremlinSourceVertex");
        }

        Assert.isTrue(source.getId().isPresent(), "GremlinSource should contain id.");

        return generateHasId(g.V(), source.getId().get());

    }

}
