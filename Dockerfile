###
# Topcoder Infromix enviroment for testing and Challenges
# Created on 3.1.6.2016 by kbowerma
# build with:  docker build  --rm=true -t spooner .
# run with:  docker run -it --name run_spoon spooner /bin/bash
#
# 0. docker build --rm=true -t spooner .
#
# 1. The command to run this cotainer attached
#
#     docker run -it --name run_spoon spooner
#
# 2. The command to run this container detached.
#
#     docker run -itd --name run_spoon spooner
#     Then:
#     docker exec -it run_spoon bash



FROM  appiriodevops/informix:latest
MAINTAINER Kyle Bowerman "kyle.bowerman@topcoder.com"
  #thanks to appbead submission 512661

#Install deps tools
  RUN yum clean all
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
  RUN mv /home/informix/myexport/* /opt/IBM/informix/bin
  RUN rmdir /home/informix/myexport


#Now get myschema
  RUN mkdir /home/informix/utils2
  WORKDIR /home/informix/utils2
  RUN wget ftp://ftp.iiug.org/pub/informix/pub/utils2_ak.gz
  RUN gunzip utils2_ak.gz
   RUN ls -ltr
  RUN  echo n | sh utils2_ak
  RUN ar -x myschema.source.ar

#Make Infomix a sudoer (not needed for secton below be used for later installation )
 RUN echo 'informix ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers




# start and Run informix
  # set environment variables
  ENV DB_LOCALE EN_US.UTF8
  ENV CLIENT_LOCALE EN_US.UTF8
  ENV INFORMIXDIR /opt/IBM/informix
  ENV LD_LIBRARY_PATH $INFORMIXDIR/lib:/lib64:/usr/lib64:$LD_LIBRARY_PATH
  ENV INFORMIXSERVER informixoltp_tcp
  ENV ONCONFIG onconfig.informixoltp_tcp
  ENV INFORMIXSQLHOSTS "/opt/IBM/informix/etc/sqlhosts.informixoltp_tcp"
  ENV JAVA_HOME /usr/share/java
  ENV ANT_HOME /usr/share/ant
  ENV PATH $INFORMIXDIR/bin:$JAVA_HOME/bin:$PATH:$HOME/bin:$ANT_HOME/bin
  WORKDIR /home/informix
  USER informix

  #TestDataTool
   COPY TestDataTool.zip /home/informix/TestDataTool.zip
   RUN unzip TestDataTool.zip

  # start informix, and KEEP PROCESS RUNNING
  CMD oninit -y && bash
