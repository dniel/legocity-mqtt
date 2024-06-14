FROM gcr.io/distroless/java
ENV USER=appuser
ENV GROUP=$USER \
    HOME=/home/$USER

WORKDIR $HOME

EXPOSE 8080

# copy application files
COPY  Docker/runapp.sh $HOME/
RUN chmod 755 $HOME/*.sh

COPY build/docker/main/layers/libs $HOME/application/libs
COPY build/docker/main/layers/resources $HOME/application/resources
COPY build/docker/main/layers/app $HOME/application

CMD [ "./runapp.sh" ]
