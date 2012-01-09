package no.sintef.cloudml

import no.sintef.cloudml.engine.Engine

object Client extends Application {

    val template = """{
        "nodes": [{
            "name": "test1", "minRam": 0
        },{
            "name": "test2", "minRam": 1000
        }]}"""

    val account = """{
        "provider": "aws-ec2", 
        "credential": {
            "identity": "...", 
            "credential": "..."
        }}"""

    val runtimeInstances = Engine(account, List(template))
    println("Got instances: ")
    runtimeInstances.foreach(instance => {
        println(instance.instance.name + ": id: " + instance.id + ". Private address: " 
          + instance.privateAddress + ". Public address: " + instance.publicAddress)
    })
    System.exit(0)
}
