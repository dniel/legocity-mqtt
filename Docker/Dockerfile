FROM azul/zulu-openjdk:21-jre-headless
#ENV USER=appuser
#ENV GROUP=$USER \
#    HOME=/home/$USER

# set timezone to Oslo, because otherwise it is set
# to the location of the datacenter running fargate.
ENV TZ=Europe/Oslo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# create app user.
#RUN addgroup $GROUP
#RUN adduser $USER --group $GROUP
#RUN chown -R $USER $HOME

#RUN apk add openjdk21-jre

WORKDIR $HOME

EXPOSE 8080

# copy application files
COPY Docker/runapp.sh $HOME/
RUN chmod 755 $HOME/*.sh

COPY build/docker/main/layers/libs $HOME/application/libs
COPY build/docker/main/layers/resources $HOME/application/resources
COPY build/docker/main/layers/app $HOME/application

CMD [ "./runapp.sh" ]