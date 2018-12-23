FROM dorowu/ubuntu-desktop-lxde-vnc


USER 0

RUN apt-get -y update && apt-get install -y \
	openjdk-8-jdk \
	maven \
	&& rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

RUN mkdir relay-test
COPY . relay-test
WORKDIR relay-test
RUN ["chmod", "+x", "./script2.sh"]


ENTRYPOINT ["./script2.sh"]