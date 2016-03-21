###
# Topcoder Infromix enviroment for testing and Challenges
# Created on 3.1.6.2016 by kbowerma
# build with:  docker build  --rm=true -t spooner .
# run with:  docker run -it --name run_spoon spooner /bin/bash
#
# All in one:
# docker build  --rm=true -t spooner . && docker rm run_spoon &&  docker run -d --name run_spoon spooner && docker exec -it run_spoon bash
###



FROM  appiriodevops/informix:latest
MAINTAINER Kyle Bowerman "kyle.bowerman@topcoder.com"
#Install deps tools
RUN yum install -y net-tools
RUN yum install -y ksh
RUN yum install -y gcc
RUN yum install -y make

#Install myexport
 RUN cd /home/informix
 RUN pwd
RUN mkdir /home/informix/myexport
RUN wget  ftp://ftp.iiug.org/pub/informix/pub/myexport.shar.gz -O /home/informix/myexport/myexport.shar.gz
RUN gunzip /home/informix/myexport/myexport.shar.gz
RUN  cd /home/informix/myexport && sh /home/informix/myexport/myexport.shar
RUN chown -R informix:informix  /home/informix/myexport
RUN chmod -R a+x /home/informix/myexport
RUN cp /home/informix/myexport/* /opt/IBM/informix/bin
  RUN ls -ltr /home/informix/myexport

#Now get myschema
RUN mkdir /home/informix/utils2
WORKDIR /home/informix/utils2
RUN wget ftp://ftp.iiug.org/pub/informix/pub/utils2_ak.gz
RUN gunzip utils2_ak.gz
 RUN ls -ltr
RUN  echo n | sh utils2_ak
RUN ar -x myschema.source.ar

#CMD ["runuser", "-l",  "informix", "/home/informix/start-informix.sh;"]
# ananthhh submission 222966
CMD runuser -l informix /home/informix/start-informix.sh && tail -f /dev/null








#CMD ["runuser", "-l",  "informix", "/home/informix/start-informix.sh"]

#ENTRYPOINT runuser -l informix /home/informix/start-informix.sh
RUN ps -A
