Postgres:
docker run --name survey-postgres -p 5432:5432 -e POSTGRES_DB=survey_db -e POSTGERS_USER=survey_user -e POSTGRES_PASSWORD=survey_pwd -d postgres

Start with gradle:
./gradlew bootRun

Test:
./gradlew test

====

Build:
./gradlew build

Start:
java -jar ./build/libs/survey-0.0.1-SNAPSHOT.jar

====

Example GET request:
localhost:8080/api/surveys?page=0&size=2&sort=name,desc