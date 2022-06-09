package org.apache.pulsar.io.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Test {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("pulsar");
        db.watch().fullDocument(FullDocument.UPDATE_LOOKUP).forEach((ChangeStreamDocument<Document> changeStreamDocument) -> {
            System.out.println(changeStreamDocument.getFullDocument());
        });
//        ChangeStreamPublisher<Document> stream = db.watch();
//        stream.batchSize(2)
//                .fullDocument(FullDocument.UPDATE_LOOKUP)
//                .startAtOperationTime(new BsonTimestamp(0L));
//
//        stream.subscribe(new Subscriber<ChangeStreamDocument<Document>>() {
//
//            @Override
//            public void onSubscribe(Subscription subscription) {
//                subscription.request(Integer.MAX_VALUE);
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onNext(ChangeStreamDocument<Document> doc) {
//                System.out.println("New change doc: " + doc);
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                System.out.println("Subscriber error" + error);
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("Subscriber complete");
//            }
//        });
    }
}
