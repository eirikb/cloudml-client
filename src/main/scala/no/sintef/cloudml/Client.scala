package no.sintef.cloudml

import no.sintef.cloudml.engine.Engine
import no.sintef.cloudml.repository.domain._

object Client extends Application {

    val template = """{
            "nodes": [{
                "name": "test1"
            },{
                "name": "test2", 
                "minRam": 10, 
                "minCores": 2, 
                "minDisk": 0, 
                "locationId": "us-west-1a"
        }]}"""

    val account = """{
            "provider": "aws-ec2", 
            "identity": "", 
            "credential": ""
        }"""

    println("Creating nodes...")
    val runtimeInstances = Engine(account, List(template))

    println("Got " + runtimeInstances.size + " nodes")

    var count = 0

    runtimeInstances.foreach(ri =>  {
        println("Adding listener to: " + ri.instance.name + " (" + ri.status + ")")
        ri.addListener( (event) =>  {
            event match {
                case Event.Property => 
                case Event.Status => 
                    println("Status changed for " + ri.instance.name + ": " + ri.status)
                    if (ri.status == Status.Started) {
                        count += 1
                        println("Node " + ri.instance.name + " is now running: " + ri)
                        println(ri.properties)
                        val id = ri.properties("id")
                        Engine.destroyNode(id)
                        println("Node " + ri.instance.name + " destroyed")
                    }
                    if (count == 2) {
                        println("Done")
                        System.exit(0)
                    }
            }
        })
    })
}
