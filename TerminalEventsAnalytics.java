package com.github.blowoffvalve.postilionAnalytics.stream;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDe;
import org.apache.avro.specific.AvroGenerated;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class TerminalEventsAnalytics {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "ubuntu:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "TerminalEventAnalyticsV1.0");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AbstractKafkaAvroSerDe.class);
        config.put(StreamsConfig.EXACTLY_ONCE, true);

        //Initialize the Streaming app
        StreamsBuilder builder = new StreamsBuilder();
        //Read in the terminal details
        GlobalKTable<String, AvroGenerated> terminalDetails = builder.globalTable("term");
        //Read in the terminal events
        KStream<String, AvroGenerated> supportEvents = builder.stream("support-events");

        //Create the topology
    }
}
