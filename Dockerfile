# 1️⃣ 빌드 단계: Gradle을 이용해 JAR 파일 만들기
FROM gradle:jdk17-jammy AS build

# 2️⃣ 소스 코드 복사 및 작업 디렉토리 설정
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# 3️⃣ Gradle 빌드 실행 (테스트 제외)
RUN gradle build --no-daemon -x test

# 4️⃣ 실행 단계: 빌드된 JAR 파일을 실행할 경량 JDK 이미지 사용
FROM eclipse-temurin:17-jdk

# 5️⃣ 작업 디렉토리 설정
WORKDIR /app

# 6️⃣ 빌드된 JAR 파일 복사
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# 7️⃣ 컨테이너에서 실행할 명령어 (Spring Boot 실행)
CMD ["java", "-jar", "app.jar"]

# 8️⃣ 컨테이너 메타데이터 추가
LABEL org.name="hezf"
