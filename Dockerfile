###
# Topcoder Infromix enviroment for testing and Challenges
# Created on 3.1.6.2016 by kbowerma
# build with:  docker build  --rm=true -t spooner .
# run with:  docker run -it --name run_spoon spooner /bin/bash
#
# 0. docker build --rm=true -t spooner .
#
# 1. The command to run this cotainer attached, and remove it when exited
#
#     docker run -it --rm --name run_spoon spooner
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
  #ENV JAVA_HOME /usr/share/java
  ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-1.7.0.91-2.6.2.1.el7_1.x86_64
  ENV ANT_HOME /usr/share/ant
  ENV PATH $INFORMIXDIR/bin:$JAVA_HOME/bin:$PATH:$HOME/bin:$ANT_HOME/bin
  WORKDIR /home/informix
  #USER informix

  #TestDataTool
   COPY TestDataToolSrc /home/informix/TestDataToolSrc
   RUN chown -R informix:informix  /home/informix/TestDataToolSrc
   USER informix
   WORKDIR  /home/informix/TestDataToolSrc/build/ant/classes
   RUN jar -cvf /home/informix/TestDataToolSrc/testDataTool.jar .
   WORKDIR  /home/informix/TestDataToolSrc
   RUN mkdir output

  # start informix, and KEEP PROCESS RUNNING
  CMD oninit -y && bash

    #  Now create and connect to the container,  Note this will remove the container once you exit.   See above to run detached
    #  docker run -it --rm --name run_spoon spooner
    #  run the script to create the sql load files:   ant run
    #  execute the load tool to insert the data:  ant bulk-test-data-load
