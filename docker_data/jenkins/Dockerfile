FROM jenkins/jenkins:jdk17

USER root
RUN apt-get update \
  && apt-get install -y sudo \
  && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

USER jenkins
RUN sudo chown -R $(whoami) /usr/local/bin
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN ls /usr/local/bin
RUN jenkins-plugin-cli -f /usr/share/jenkins/plugins.txt 
RUN curl -L "https://github.com/docker/compose/releases/download/v2.2.3/docker-compose-linux-x86_64" -o /usr/local/bin/docker-compose
RUN chmod +x /usr/local/bin/docker-compose