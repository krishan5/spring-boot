# spring-boot
Spring boot and Spring cloud learning

Topics covered :
1. Springboot auto configuration main keys features and logging
  Files : application.properties
2. @PathVariable, @RestController, @GetMapping, @RequestMapping, @PostMapping, @DeleteMapping
  Files : HelloWorldController.java, UserApi.java
3. Endpoints.
  Files : UserApi.java, UserDaoService.java, application.properties, UserNotFoundException.java
4. Customized general level exception handler
  Files : CustomizedResponseEntityExceptionHandler.java, ExceptionResponse.java
5. Validation like @Valid, @Past, @Size, @NotNull
  Files : build.gradle, UserApi.java, User.java, CustomizedResponseEntityExceptionHandler.java
6. HATEOAS.
  Files : build.gradle, UserApi.java
7. i18n.
  Files : HelloWorldController.java, messages.properties, message_fr.properties, message_nl.properties
8. Content negotiation.
  Files : build.gradle
9. Swagger and OpenAPI.
  Files : build.gradle
10. Spring Actuator.
  Files : build.gradle, application.properties
11. HAL explorer.
  Files : build.gradle
12. Static and Dynamic filtering of class variables on response.
  Files : User.java, FilteringController.java, SomeBean.java
13. Versioning.
  Files : VersionController.java
14. Basic Authentication.
  Files : build.gradle, application.properties
15. Richardson Maturity Model.
  It helps in identifying "How Restful are you ?". 
  It defines 3 different levels of Restful services.
  Level 0 : Expose web services in Rest sytle i.e. started thinking about Rest implementation.
  Level 1 : Expose resouces with proper URI.
  Level 2 : Level 1 + HTTP Methods.
  Level 3 : Level 2 + HATEOAS. (data + also next possible actions) here we are not only talking about the information of the request, 
  but also telling what next possible action can be taken.
 
