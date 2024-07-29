# Feature test framework

## How to use

### How to start

`./gradlew run -Dcucumber.filter.tags="@Example" -P args="-p pretty -p json:build/reports/cucumber.json"`
### How to start in debug mode, with parameterized report destination (Gradle)

`./gradlew run -Dcucumber.filter.tags="@Example" -D featuretest.logging.level.com.examnple.featuretest=debug -P args="-p pretty -p json:build/reports/cucumber.json"`

### Debug mode settings to log all http requests and responses in case of IDEA run configuration
VM options:
`-Dlogging.level.com.example.featuretest.core.LoggingInterceptor=DEBUG -Dlogging.level.org.springframework.web.client.RestTemplate=DEBUG`

### How to run from idea
* Run config template: Application
* Main class: Cucumber's main class (com.example.featuretest.FeatureTest)
* VM options: -Dcucumber.filter.tags="@Example and not @WIP"

## Reports

`./gradlew generateCucumberReports`
