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

### Used libraries
A list of used dependencies can be found within the pom.xml

### Usage example
```java
HablameClient client = new HablameClient();
Future<HttpResponse<String>> future = client.getReplyForMessageAsync( "Wie ist das wetter in wuerzburg" );
HttpResponse<String> response = future.get();
String answer = response.getBody();
System.out.println( answer );
```

More examples can be found in the unit tests

### Version History
name, date(dd/mm/yyyyy)
 - [Pre-Release Hablame Service-API v0.1-beta.1](https://github.com/TeamChatbot/hablame-service-api/releases/tag/v0.1-beta.1) (25/08/2015)
 - [Pre-Release Hablame Service-API v0.1-beta.2](https://github.com/TeamChatbot/hablame-service-api/releases/tag/v0.1-beta.2) (25/08/2015)
 - [Rekease Hablame Service-API Projectpaper Summer 2015](https://github.com/TeamChatbot/hablame-service-api/releases/tag/v1.0) (22/09/2015)
