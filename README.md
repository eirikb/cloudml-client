Running example client
----------------------

First you need cloudml-engine to be in the local repo, and correct version.  
We are currently not deploying any releases at the moment, so commit must be specified.  
This can be done like this:

    git clone git@github.com:eirikb/cloudml-engine
    cd cloudml-engine
    git checkout 4e8807da8ff5353fe0b64e63056f830a4e66b3bd
    mvn clean install

Then you can run the client like this:

    <Edit src/main/scala/no/sintef/cloudml/Client.scala to have correct identity and credential>
    mvn scala:run
