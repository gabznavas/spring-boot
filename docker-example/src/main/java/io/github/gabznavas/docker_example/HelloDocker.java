package io.github.gabznavas.docker_example;

import java.util.Objects;

public class HelloDocker {

    private final String content;
    private final String environment;

    public HelloDocker(String content, String environment) {
        this.content = content;
        this.environment = environment;
    }

    public String getContent() {
        return content;
    }

    public String getEnvironment() {
        return environment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HelloDocker that = (HelloDocker) o;
        return Objects.equals(content, that.content) && Objects.equals(environment, that.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, environment);
    }
}
