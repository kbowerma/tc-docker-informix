#tc-docker-informix

This project will setup a docker image and container for Topcoders Informix environment.  It will include a TestData tool that will generate test records and [myexport](http://www.iiug.org/software/archive/myexport_shar.README_05262014.txt)

##Setup Instructions:

1.  Clone this repo

   ``` git clone https://github.com/kbowerma/tc-docker-informix.git ```

2.  ```cd tc-docker-informix```

3.  Bulid the image and remove any intermidate images, call the image spooner

   ```docker build --rm=true -t spooner .```

4.  Run the container interactively, call it run_spoon and remove it when you exit

    ```docker run -it --rm --name run_spoon spooner```

5.  cd TestDataToolSrc & generate sample records

     ```ant run```

6.  insert the sample records

    ```ant bulk-test-data-load```
