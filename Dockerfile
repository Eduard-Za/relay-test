FROM dorowu/ubuntu-desktop-lxde-vnc:latest

USER 0

RUN apt-get -y update && apt-get install -y \
    xvfb \
	openjdk-8-jdk \
	maven \
	&& rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

RUN mkdir relay-test
COPY . relay-test
WORKDIR relay-test


# USE IF YOU WANT TO RUN IN HEADLESS MODE

# RUN ["chmod", "+x", "./headless.sh"]
# ENTRYPOINT ["./headless.sh"]