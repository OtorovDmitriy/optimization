package ru.optimization;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class BenchmarkLoop {
    Message messageObj;
    String messageSerializationExampleJSON;
    String messageSerializationExampleXML;
    Gson gson;
    XStream xstream;

    @Setup
    public void setupJsonXML() {
        messageObj = new Message(1, 2, "Hello world", true);
        xstream = new XStream();
        gson = new Gson();

        messageSerializationExampleJSON = gson.toJson(messageObj);
        messageSerializationExampleXML = xstream.toXML(messageObj);

        System.out.println("Do Setup");
    }

    @TearDown
    public void doTearDown() {
        System.out.println("Do TearDown");
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testJsonSerialize(Blackhole bh) {
        bh.consume(gson.toJson(messageObj));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testJsonDeserialize(Blackhole bh) {
        bh.consume(gson.fromJson(messageSerializationExampleJSON, Message.class));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testXMLSerializeXStream(Blackhole bh) {
        bh.consume(xstream.toXML(messageObj));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testXMLDeserializeXStream(Blackhole bh) {
        bh.consume(xstream.fromXML(messageSerializationExampleXML));
    }
}
