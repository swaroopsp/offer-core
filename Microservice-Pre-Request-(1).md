![Data Flow](https://github.com/ConnectedHomes/bg-rest-api/raw/master/images/Data%20Flow.png)

JsonEntityConverter

- Located in bg-mod-jsonapi
- Contains conversion methods and handles validation for you
- Accepts json and returns an object that you specify the type of

Entity

- Models must make use of Entity in order to be used by JsonEntityConverter

@Type("classname")
- Each model has this annotation
- Each model has a unique type

 @RelatesTo(Address.class)


public class EntityReader implements MessageBodyReader<Entity> 