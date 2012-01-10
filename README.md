Running example client
--

First you need cloudml-engine to be in the local repo, this can be done like this:

    git clone git@github.com:eirikb/cloudml-engine
    cd cloudml-engine
    mvn clean install

Then you can run the client like this:

    <Edit src/main/scala/no/sintef/cloudml/Client.scala to have correct identity and credential>
    mvn scala:run
