# Hablame Service API
### What this repository is about
Within this repo you can find a simple API to use when accessing the [Hablame-Botbackend service](https://github.com/TeamchatBot/Hablame-Botbackend).
It simplifies the REST-usage of the service and conatains methods to every REST interface which is currently available.


### Structure of this repository
Sourcecode:
 - api code: [src/main/java/de/fhws/hablame/service/api](https://github.com/TeamChatbot/hablame-service-api/tree/master/HablameServiceAPI/src/main/java/de/fhws/hablame/service/api)
 - unit tests: [src/test/java/de/fhws/hablame/service/api/test](https://github.com/TeamChatbot/hablame-service-api/tree/master/HablameServiceAPI/src/test/java/de/fhws/hablame/service/api/test)
 
Documentation:
 - [JavaDocs](https://github.com/TeamChatbot/hablame-service-api/tree/master/HablameServiceAPI/JavaDoc)

### Usage example
```java
HablameClient client = new HablameClient();
Future<HttpResponse<String>> future = client.getReplyForMessageAsync( "Wie ist das wetter in wuerzburg" );
HttpResponse<String> response = future.get();
String answer = response.getBody();
System.out.println( answer );
```

More examples can be found in the unit tests
