package no.sintef.cloudml

import no.sintef.cloudml.engine.Engine
import no.sintef.cloudml.repository.domain._

object Client {

    def main(args: Array[String]) {
        if (args.length < 2) {
            println("Need at least two arguments, account file and one template file")
        } else {
            val account = readFile(args(0))
            val templates = args.slice(1, args.length).map(file => readFile(file))
            createNodes(account, templates.toList)
        }
   }

    def readFile(filename: String) = {
        val file = io.Source.fromFile(filename)
        val lines = file.mkString
        file.close()
        lines
    }

    def createNodes(account: String, templates: List[String]) {
        println("Creating nodes...")
        val runtimeInstances = Engine(account, templates)

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
}
