FROM openjdk
COPY . /src/main/java/ /tmp/
WORKDIR /tmp
RUN javac Main.java
CMD ["java", "Main"]